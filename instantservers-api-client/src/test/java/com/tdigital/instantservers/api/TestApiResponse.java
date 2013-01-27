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

import junit.framework.Assert;

import org.junit.Test;

import com.tdigital.instantservers.model.Empty;

public class TestApiResponse {
    @Test
    public void code200ShouldBeSuccess() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.setStatusCode(200);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void code2xxShouldBeSuccess() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.setStatusCode(299);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void code1xxShouldBeFailure() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.setStatusCode(100);
        Assert.assertFalse(response.isSuccess());
    }

    @Test
    public void code4xxShouldBeFailure() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.setStatusCode(400);
        Assert.assertFalse(response.isSuccess());
    }

    @Test
    public void code5xxShouldBeFailure() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.setStatusCode(500);
        Assert.assertFalse(response.isSuccess());
    }

    @Test
    public void shouldStoreHeaders() {
        InstantServersApiResponse<Empty> response = new InstantServersApiResponse<Empty>();
        response.addHeader("name", "value1");
        response.addHeader("name", "value2");
        Assert.assertEquals("value2", response.getHeader("name"));
    }
}
