/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api.billing;

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.HttpHeaders;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.api.InstantServersApiResponse;
import com.tdigital.instantservers.api.InstantServersApiUtils;
import com.tdigital.instantservers.model.Empty;
import com.tdigital.instantservers.model.billing.Report;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillingAPIClient extends BaseAPIClient {
    public BillingAPIClient(final String login, final String password, final String endpoint) {
        super(login, password, endpoint);
    }

    public String requestGlobalReport(final Date from, final Date to, final String[] machineUUID) throws InstantServersApiException {
        return requestCustomerReport(from, to, machineUUID, null);
    }

    public String requestCustomerReport(final Date from, final Date to, final String[] machineUUID, final String customerUUID) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("from", InstantServersApiUtils.dateToString(from), formParams);
        tryAddParam("to", InstantServersApiUtils.dateToString(to), formParams);
        tryAddParam("machines", machineUUID, formParams);

        InstantServersApiResponse<Empty> response = null;
        if (customerUUID != null) {
            response = post("/customers/{id}/usage", null, Empty.class, formParams, customerUUID);
        } else {
            response = post("/usage", null, Empty.class, formParams, null);
        }

        // Location: /usage?from=2011-10-31T14:36:46.881Z&to=2011-11-03T22:59:42.635Z
        String location = response.getHeader(HttpHeaders.LOCATION);
        return location;
    }

    public Report getReport(final String location) throws InstantServersApiException {
        return get(location, Report.class).getValue();
    }

    public Report getGlobalReport(final Date from, final Date to, final String[] machineUUID) throws InstantServersApiException {
        return getCustomerReport(from, to, machineUUID, null);
    }

    public Report getCustomerReport(final Date from, final Date to, final String[] machineUUID, final String customerUUID) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("from", InstantServersApiUtils.dateToString(from), formParams);
        tryAddParam("to", InstantServersApiUtils.dateToString(to), formParams);
        tryAddParam("machines", machineUUID, formParams);

        InstantServersApiResponse<Report> response = null;
        if (customerUUID != null) {
            response = get("/customers/{id}/usage", Report.class, formParams, customerUUID);
        } else {
            response = get("/usage", Report.class, formParams);
        }

        return response.getValue();
    }
}
