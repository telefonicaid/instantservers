/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.utils;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.GeneralSecurityException;

public class HttpClientFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientFactory.class);

    private static final int HTTP_DEFAULT_PORT = 80;
    private static final int HTTPS_DEFAULT_PORT = 443;

    private static final int DEFAULT_MAX_CONNECTIONS = 200;

    // Maximum time waited for the server to response, in an opened connection
    private static final int DEFAULT_TIMEOUT = 30000;
    // Maximum time waited for opening a new connection
    private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;

    private static final TrustManager TRUST_MANAGER = new AcceptAllTrustManager();

    /**
     * Creates a {@link org.apache.http.client.HttpClient} that TRUSTS ANY CERTIFICATE.
     * Use it with caution in your production environment.
     */
    public static DefaultHttpClient createHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(params, DEFAULT_TIMEOUT);
        HttpConnectionParams.setConnectionTimeout(params, DEFAULT_CONNECTION_TIMEOUT);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { TRUST_MANAGER }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme("https", HTTPS_DEFAULT_PORT, ssf));
        } catch (GeneralSecurityException e) {
            LOGGER.warn("Security algorithm TLS not found", e);
        }

        schemeRegistry.register(new Scheme("http", HTTP_DEFAULT_PORT, PlainSocketFactory.getSocketFactory()));

        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);
        cm.setMaxTotal(DEFAULT_MAX_CONNECTIONS);
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS);

        return new DefaultHttpClient(cm, params);
    }
}
