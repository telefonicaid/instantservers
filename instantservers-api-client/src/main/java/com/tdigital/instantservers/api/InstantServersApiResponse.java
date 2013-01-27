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

import java.util.HashMap;
import java.util.Map;

public class InstantServersApiResponse<T> {
    private T value;
    private Map<String, String> headers = new HashMap<String, String>();

    private int statusCode;
    private String statusMessage;

    public InstantServersApiResponse() {
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void addHeader(String name, String value) {
        headers.put(name,  value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isSuccess() {
        return statusCode / 100 == 2; // 2xx
    }

    public boolean isEmptyBody() {
        return statusCode == 202;
    }
}
