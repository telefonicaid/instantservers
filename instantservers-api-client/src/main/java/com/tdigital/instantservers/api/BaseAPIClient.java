/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api;

import com.tdigital.instantservers.model.Empty;
import com.tdigital.instantservers.model.ErrorResult;
import com.tdigital.instantservers.utils.HttpClientFactory;
import com.tdigital.instantservers.utils.JSONUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public abstract class BaseAPIClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPIClient.class);

    private static final String JOYENT_X_API_VERSION = "~6.5";

    private String endpoint;
    private String login;
    private String password;
    private String version;

    private final ClientExecutor executor;

    public BaseAPIClient(String login, String password, String endpoint) {
        this(login, password, endpoint, JOYENT_X_API_VERSION);
    }

    public BaseAPIClient(String login, String password, String endpoint, String version) {
        this.login = login;
        this.password = password;
        this.endpoint = endpoint;
        this.version = version;

        executor = createExecutor();
    }

    private ClientExecutor createExecutor() {
        DefaultHttpClient client = HttpClientFactory.createHttpClient();

        Credentials creds = new UsernamePasswordCredentials(login, password);
        client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), creds);

        addPreemptiveAuth(client);
        addHeaders(client);

        return new ApacheHttpClient4Executor(client);
    }

    /**
     * Add custom HTTP headers that Joyent API endpoints need to work.
     * @param httpClient
     */
    protected void addHeaders(DefaultHttpClient httpClient) {
        final HttpRequestInterceptor headers = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                request.addHeader(HttpHeaders.X_API_VERSION, version);
            }
        };
        httpClient.addRequestInterceptor(headers, 1);
    }

    private void addPreemptiveAuth(DefaultHttpClient httpClient) {
        final HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
                CredentialsProvider credsProvider =
                        (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);

                if (authState.getAuthScheme() == null) {
                    AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
                    Credentials creds = credsProvider.getCredentials(authScope);
                    if (creds != null) {
                        authState.setAuthScheme(new BasicScheme());
                        authState.setCredentials(creds);
                    }
                }
            }
        };
        httpClient.addRequestInterceptor(preemptiveAuth, 0);
    }

    @ClientInterceptor
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.WILDCARD })
    private class ErrorReaderInterceptor implements MessageBodyReaderInterceptor {
        @Override
        public Object read(MessageBodyReaderContext context) throws IOException {
            // Check if there is an error in the headers (MAPI)
            String errorCode = context.getHeaders().getFirst(HttpHeaders.X_JOYENT_ERROR_CODE);
            if (errorCode != null) {
                return errorCode;
            } else {
                return context.proceed();
            }
        }
    }

    @ClientInterceptor
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.WILDCARD })
    private class JsonReaderIntercetor<K> implements MessageBodyReaderInterceptor {
        private final Class<K> resultClazz;

        public JsonReaderIntercetor(Class<K> resultClazz) {
            this.resultClazz = resultClazz;
        }

        @Override
        public Object read(MessageBodyReaderContext context) throws IOException {
            String body = readJsonStream(context.getInputStream());
            LOGGER.debug("JSON READER << " + body);

            String error = checkCAPIResult(body, context.getMediaType());
            if (error != null) {
                return error;
            }

            // No CAPI error found, parse the object as normal
            return JSONUtils.deserialize(body, resultClazz);
        }

        /**
         * Checks if there is an error in the body (CAPI).
         */
        private String checkCAPIResult(String body, MediaType mediaType) throws IOException {

            try {
                ErrorResult errorResult = JSONUtils.deserialize(body, ErrorResult.class);
                if (errorResult.getErrors() != null) {
                    // Sends only the first error
                    return errorResult.getErrors()[0];
                }
            } catch (JsonMappingException em) {
                // The JSON is not parseable as an ErrorResult (this happens, for example,
                // when retrieving an array)
            } catch (JsonParseException ep) {
                // CAPI - getCustomerMetada answer with text/hmtl content-type
                if (mediaType.equals(MediaType.TEXT_HTML_TYPE)) {
                    return body;
                } else {
                    throw ep;
                }
            }
            return null;
        }

        private String readJsonStream(InputStream is) throws IOException {
            StringBuilder result = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.length() > 0 ? result.toString() : "{}";
        }
    }

    /**
     *
     * @param method HTTP method name to execute (get, post, delete, put ...)
     * @param path resource URI to be retrieved (i.e. "/zones")
     * @param requestBodyData Object to be sent as the request body
     * @param resultClazz Type of the result
     * @param pathParams URI dynamic paramters
     * @return
     * @throws InstantServersApiException
     */
    private <T, K> InstantServersApiResponse<K> execute(String method, String path, T requestBodyData, final Class<K> resultClazz, Object ... pathParams) throws InstantServersApiException {
        return execute(method, path, requestBodyData, resultClazz, null, pathParams);
    }

    protected ClientRequest createRequest(String path) {
        return new ClientRequest(endpoint + path, executor);
    }

    /**
     * 
     * @param method HTTP method name to execute (get, post, delete, put ...)
     * @param path resource URI to be retrieved (i.e. "/zones")
     * @param requestBodyData Object to be sent as the request body
     * @param resultClazz Type of the result
     * @param formParams POST params
     * @param pathParams URI dynamic paramters
     * @return
     * @throws InstantServersApiException
     */
    private <T, K> InstantServersApiResponse<K> execute(
            String method, String path, T requestBodyData, final Class<K> resultClazz,
            Map<String, Object> formParams, Object ... pathParams) throws InstantServersApiException {
        ClientRequest request = createRequest(path);

        ClientResponse<K> response = null;
        try {
            request.accept(MediaType.APPLICATION_JSON_TYPE);

            if (formParams != null && !formParams.isEmpty()) {
                addRequestParameters(method, formParams, request);
            } else if (requestBodyData != null) {
                // You cannot send both form parameters and an entity body
                if (requestBodyData instanceof String) {
                    // String bodies are sent as plain text, they are not serialized to json, i.e. CAPI
                    request.body(MediaType.TEXT_PLAIN_TYPE, requestBodyData);
                } else {
                    String body = JSONUtils.serialize(requestBodyData);
                    request.body(MediaType.APPLICATION_JSON_TYPE, body);
                }
            }

            if (pathParams != null && pathParams.length > 0) {
                request.pathParameters(pathParams);
            }

            assert resultClazz != null;

            request.getReaderInterceptorList().add(new ErrorReaderInterceptor());
            request.getReaderInterceptorList().add(new JsonReaderIntercetor<K>(resultClazz));

            response = request.httpMethod(method, resultClazz);
        } catch (Exception e) {
            // something went wrong, this does not include 4xx errors
            if (response != null) {
                response.releaseConnection();
            }

            throw new InstantServersApiRuntimeException(e);
        }

        try {
            InstantServersApiResponse<K> result = processResponse(request, response);
            return result;
        } finally {
            // release the connection in any case
            // see https://issues.jboss.org/browse/RESTEASY-621 for more information
            // about the need of closing/releasing the underlying connection.
            response.releaseConnection();
        }
    }

    private void addRequestParameters(String method, Map<String, Object> formParams, ClientRequest request) {
        Set<Map.Entry<String, Object>> entries = formParams.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (method.equalsIgnoreCase("POST")) {
                Object value = entry.getValue();
                if (value instanceof Object[]) {
                    Object[] values = (Object[]) value;
                    for (Object object : values) {
                        request.formParameter(entry.getKey(), object);
                    }
                } else {
                    request.formParameter(entry.getKey(), entry.getValue());
                }
            } else {
                request.queryParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    protected <T> InstantServersApiResponse<Empty> post(String path, T requestBodyData) throws InstantServersApiException {
        return post(path, requestBodyData, Empty.class);
    }

    protected <T, K> InstantServersApiResponse<K> post(String path, T requestBodyData, Class<K> resultClazz) throws InstantServersApiException {
        return post(path, requestBodyData, resultClazz, null);
    }

    protected <T, K> InstantServersApiResponse<K> post(String path, T requestBodyData, Class<K> resultClazz, Map<String, Object> formParams) throws InstantServersApiException {
        return post(path, requestBodyData, resultClazz, formParams, null);
    }

    protected <T, K> InstantServersApiResponse<K> post(String path, T requestBodyData, Class<K> resultClazz, Map<String, Object> formParams, String ... params) throws InstantServersApiException {
        return execute("POST", path, requestBodyData, resultClazz, formParams, params);
    }

    protected InstantServersApiResponse<Empty> post(String path, Map<String, Object> formParams) throws InstantServersApiException {
        return post(path, null, Empty.class, formParams);
    }

    protected <K> InstantServersApiResponse<K> post(String path, Class<K> resultClazz, Map<String, Object> formParams) throws InstantServersApiException {
        return post(path, null, resultClazz, formParams);
    }

    protected <T> InstantServersApiResponse<Empty> post(String path, T requestBodyData, Map<String, Object> formParams) throws InstantServersApiException {
        return post(path, requestBodyData, Empty.class, formParams);
    }

    protected <T> InstantServersApiResponse<Empty> put(String path, T requestBodyData) throws InstantServersApiException {
        return put(path, requestBodyData, Empty.class);
    }

    protected <T, K> InstantServersApiResponse<K> put(String path, T requestBodyData, Class<K> resultClazz) throws InstantServersApiException {
        return put(path, requestBodyData, resultClazz, null);
    }

    protected <T, K> InstantServersApiResponse<K> put(String path, T requestBodyData, Class<K> resultClazz, Map<String, Object> formParams) throws InstantServersApiException {
        return put(path, requestBodyData, resultClazz, formParams, null);
    }

    protected <T, K> InstantServersApiResponse<K> put(String path, T requestBodyData, Class<K> resultClazz, Map<String, Object> formParams, String ... params) throws InstantServersApiException {
        return execute("PUT", path, requestBodyData, resultClazz, formParams, params);
    }

    protected InstantServersApiResponse<Empty> put(String path, Map<String, Object> formParams) throws InstantServersApiException {
        return put(path, null, Empty.class, formParams);
    }

    protected <K> InstantServersApiResponse<K> put(String path, Class<K> resultClazz, Map<String, Object> formParams) throws InstantServersApiException {
        return put(path, null, resultClazz, formParams);
    }

    protected <T> InstantServersApiResponse<Empty> put(String path, T requestBodyData, Map<String, Object> formParams) throws InstantServersApiException {
        return put(path, requestBodyData, Empty.class, formParams);
    }

    protected InstantServersApiResponse<Empty> delete(String path, String ... params) throws InstantServersApiException {
        return execute("DELETE", path, null, Empty.class, params);
    }

    protected InstantServersApiResponse<Empty> delete(String path, Map<String, Object> formParams, String ... params) throws InstantServersApiException {
        return execute("DELETE", path, null, Empty.class, formParams, params);
    }

    protected <K> InstantServersApiResponse<K> get(String path, Class<K> resultClazz) throws InstantServersApiException {
        return get(path, resultClazz, (String[]) null);
    }

    protected <K> InstantServersApiResponse<K> get(String path, Class<K> resultClazz, String ... params) throws InstantServersApiException {
        return get(path, resultClazz, null, params);
    }

    protected <K> InstantServersApiResponse<K> get(String path, Class<K> resultClazz, Map<String, Object> formParams, String ... params) throws InstantServersApiException {
        return execute("GET", path, null, resultClazz, formParams, params);
    }

    protected void tryAddParam(String key, int value, Map<String, Object> formParams) {
        if (value != 0) {
            tryAddParam(key, Integer.toString(value), formParams);
        }
    }

    protected void tryAddParam(String key, Object value, Map<String, Object> formParams) {
        if (value != null) {
            formParams.put(key, value);
        }
    }

    private <K> InstantServersApiResponse<K> processResponse(ClientRequest request, ClientResponse<K> response)
            throws InstantServersApiException {
        try {
            LOGGER.debug("REQUEST = " + request.getHttpMethod() + " " + request.getUri());
        } catch (Exception e) {
            LOGGER.debug("REQUEST = " + request.getHttpMethod() + " uri not available");
        }
        LOGGER.debug(">> " + request.getBody());
        LOGGER.debug(">> " + request.getFormParameters());

        LOGGER.debug("RESPONSE = " + response.getStatus() + " - " + response.getResponseStatus());

        MultivaluedMap<String, String> headers = response.getHeaders();
        Set<String> headerNames = headers.keySet();
        for (String header : headerNames) {
            LOGGER.debug(">> header " + header + " = " + headers.getFirst(header));
        }

        if (response.getStatus() > 299) {
            InstantServersApiException ex = new InstantServersApiException();
            ex.setStatusCode(response.getStatus());
            ex.setInfrastructureErrorCode(response.getHeaders().getFirst(HttpHeaders.X_JOYENT_ERROR_CODE));
            // Check if entity is a string (that contains error message)
            try {
                Object entity = response.getEntity();
                if (entity instanceof String) {
                    ex.setInfrastructureErrorMessage((String) entity);
                    ex.setStatusMessage(response.getResponseStatus().getReasonPhrase() + " - " + entity);
                } else {
                    ex.setStatusMessage(response.getResponseStatus().getReasonPhrase());
                }
            } catch (Exception entityException) {
                ex.setStatusCode(response.getStatus());
                ex.setStatusMessage(response.getResponseStatus().getReasonPhrase());
            } finally {
                throw ex;
            }
        }

        final InstantServersApiResponse<K> result = new InstantServersApiResponse<K>();
        result.setStatusCode(response.getStatus());
        result.setStatusMessage(response.getResponseStatus().getReasonPhrase());

        if (result.isEmptyBody() || emptyContent(headers)) {
            LOGGER.debug("<< empty body");
        } else {
            result.setValue(response.getEntity());
            LOGGER.debug("<< " + JSONUtils.trySerialize(result.getValue()));
        }

        for (String header : headerNames) {
            result.addHeader(header, headers.getFirst(header)); // to simplify, keep the first one
        }

        return result;
    }
    private boolean emptyContent(MultivaluedMap<String, String> headers) {
        return (headers.getFirst("Content-Length") != null
                && headers.getFirst(org.apache.http.HttpHeaders.CONTENT_LENGTH).equals("0"));
    }
}
