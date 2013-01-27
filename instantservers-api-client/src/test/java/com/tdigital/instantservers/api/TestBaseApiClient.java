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

import com.tdigital.instantservers.utils.JSONUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

public class TestBaseApiClient {
    private BaseAPIClient client;
    private ClientRequest request;
    private ClientResponse response;

    @Before
    public void init() throws Exception {
        request = Mockito.mock(ClientRequest.class);
        Mockito.when(request.body(Matchers.any(MediaType.class), Matchers.any(Class.class))).thenReturn(request);

        response = Mockito.mock(ClientResponse.class);
        Mockito.when(response.getHeaders()).thenReturn(new MultivaluedMapImpl<String, String>());
        Mockito.when(response.getResponseStatus()).thenReturn(Response.Status.OK);
        Mockito.when(request.httpMethod(Matchers.anyString(), Matchers.any(Class.class))).thenReturn(response);

        client = new BaseAPIClient("", "", "") {
            @Override
            protected ClientRequest createRequest(String path) {
                return request;
            }
        };
    }

    @Test
    public void shouldSerializeStringAsPlainText() throws Exception {
        String body = "this_is_a_string_body";
        client.put("", body);

        Mockito.verify(request).body(MediaType.TEXT_PLAIN_TYPE, body);
    }

    @Test
    public void shouldSerializeObjectAsJson() throws Exception {
        Object obj = new HashMap<String, String>();
        client.put("", obj);

        String body = JSONUtils.serialize(obj);
        Mockito.verify(request).body(MediaType.APPLICATION_JSON_TYPE, body);
    }
}
