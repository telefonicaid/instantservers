/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api.management;

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.api.InstantServersApiResponse;
import com.tdigital.instantservers.model.management.VirtualMachine;
import com.tdigital.instantservers.model.management.Zone;
import com.tdigital.instantservers.utils.JSONUtils;
import junit.framework.Assert;
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
import java.util.Map;

public class ManagementAPIClientTest {
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
    }

    @Test
    public void shouldListZones() throws Exception {
        // This client will return 1000 results the first time it's called, but only 200 results the second time
        ManagementAPIClient client = new ManagementAPIClient("", "", "") {
            @Override
            protected <K> InstantServersApiResponse<K> get(String path, Class<K> resultClazz,
                                                           Map<String, Object> formParams,
                                                           String... params)
                    throws InstantServersApiException {
                InstantServersApiResponse<K> response = new InstantServersApiResponse<K>();
                int elementsToInsert = Integer.parseInt(params[params.length - 1]) == 0 ? 1000 : 200;
                Zone[] zones = new Zone[elementsToInsert];
                @SuppressWarnings(value = "unchecked")
                K result = (K) zones;
                for (int i = 0; i < elementsToInsert; i++) {
                    zones[i] = new Zone();
                }
                response.setValue(result);
                return response;
            }
        };

        Assert.assertEquals(1200, client.listZones("customer").length);
        Assert.assertEquals(1200, client.listZones().length);
    }

    @Test
    public void shouldListVms() throws Exception {
        // This client will return 1000 results the first time it's called, but only 200 results the second time
        ManagementAPIClient client = new ManagementAPIClient("", "", "") {
            @Override
            protected <K> InstantServersApiResponse<K> get(String path, Class<K> resultClazz,
                                                           Map<String, Object> formParams,
                                                           String... params)
                    throws InstantServersApiException {
                InstantServersApiResponse<K> response = new InstantServersApiResponse<K>();
                int elementsToInsert = Integer.parseInt(params[params.length - 1]) != 3000 ? 1000 : 200;
                VirtualMachine[] zones = new VirtualMachine[elementsToInsert];
                @SuppressWarnings(value = "unchecked")
                K result = (K) zones;
                for (int i = 0; i < elementsToInsert; i++) {
                    zones[i] = new VirtualMachine();
                }
                response.setValue(result);
                return response;
            }
        };

        Assert.assertEquals(3200, client.listVirtualMachines("customer").length);
        Assert.assertEquals(3200, client.listVirtualMachines().length);
    }
}
