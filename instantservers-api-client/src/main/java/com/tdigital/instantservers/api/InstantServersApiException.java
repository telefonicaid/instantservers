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

public class InstantServersApiException extends Exception {
    private static final long serialVersionUID = 6122379869466424818L;

    private int statusCode;
    private String statusMessage;

    private String infrastructureErrorCode;
    private String infrastructureErrorMessage;

    public InstantServersApiException() {
    }

    public InstantServersApiException(String message) {
        super(message);
    }

    public InstantServersApiException(Throwable t) {
        super(t);
    }

    public InstantServersApiException(String message, Throwable t) {
        super(message, t);
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
    public String getInfrastructureErrorCode() {
        return infrastructureErrorCode;
    }
    public void setInfrastructureErrorCode(String infrastructureErrorCode) {
        this.infrastructureErrorCode = infrastructureErrorCode;
    }
    public String getInfrastructureErrorMessage() {
        return infrastructureErrorMessage;
    }
    public void setInfrastructureErrorMessage(String infrastructureErrorMessage) {
        this.infrastructureErrorMessage = infrastructureErrorMessage;
    }
}
