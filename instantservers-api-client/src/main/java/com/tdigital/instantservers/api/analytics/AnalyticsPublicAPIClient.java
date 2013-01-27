/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api.analytics;

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.model.analytics.AnalyticsSchema;
import com.tdigital.instantservers.model.analytics.DecompositionInstrumentationValue;
import com.tdigital.instantservers.model.analytics.Instrumentation;
import com.tdigital.instantservers.model.analytics.SimpleInstrumentationValue;
import com.tdigital.instantservers.utils.JSONUtils;

import javax.ws.rs.core.UriBuilder;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cloud Analytics using the Public Cloud API.  Exposes the same CaAPI with different URIs, and using
 * the customer login as part of the URI.
 */
public class AnalyticsPublicAPIClient extends BaseAPIClient {

    public AnalyticsPublicAPIClient(String login, String password, String endpoint) {
        super(login, password, endpoint);
    }

    public AnalyticsSchema describeAnalytics(String customerLogin) throws InstantServersApiException {
        return get("/" + customerLogin + "/analytics", AnalyticsSchema.class).getValue();
    }

    public Instrumentation[] listInstrumentations(String customerLogin) throws InstantServersApiException {
        return get("/" + customerLogin + "/analytics/instrumentations", Instrumentation[].class).getValue();
    }

    public Instrumentation createInstrumentation(String customerLogin, String module, String stat) throws InstantServersApiException {
        return post("/" + customerLogin + "/analytics/instrumentations?module={module}&stat={stat}", null, Instrumentation.class, null, module, stat).getValue();
    }

    public Instrumentation createInstrumentation(String customerLogin, String module, String stat, String decomposition) throws InstantServersApiException {
        return post("/" + customerLogin + "/analytics/instrumentations?module={module}&stat={stat}&decomposition={decomposition}", null, Instrumentation.class, null, module, stat, decomposition).getValue();
    }

    public Instrumentation createInstrumentation(String customerLogin, Instrumentation input) throws InstantServersApiException {

        //		clone	Number	An existing instrumentation
        //		module	String	The CA module
        //		stat	String	The CA stat
        //		predicate	String	Must be a JSON string (i.e. { eq: [ fieldname, value ] })
        //		decomposition	String	An array of arrays
        //		granularity	Number	default is 1: number of seconds between data points
        //		retention-time	Number	How long to keep this instrumentation data for
        //		persist-data	Boolean	Whether or not to store this for historical analysis
        //		idle-max	Number	number of seconds after which if the instrumentation or its data has not been accessed via the API the service may delete the instrumentation and its data

        StringBuilder queryString = new StringBuilder();
        List<String> parameters = new ArrayList<String>();
        try {
            if (input.getModule() != null) {
                queryString.append("&module={module}");
                parameters.add(input.getModule());
            }
            if (input.getStat() != null) {
                queryString.append("&stat={stat}");
                parameters.add(input.getStat());
            }
            if (input.getDecomposition() != null) {
                if (input.getDecomposition().length > 1) {
                    throw new IllegalArgumentException("Only one decomposition is allowed");
                }

                queryString.append("&decomposition={decomposition}");
                parameters.add(input.getDecomposition()[0].toString());
            }
            if (input.getPredicate() != null) {
                queryString.append("&predicate={predicate}");

                String param = JSONUtils.serialize(input.getPredicate());
                String encodedParam = UriBuilder.fromPath("{arg}").build(param).toString();
                parameters.add(encodedParam);
            }
            if (input.getIdleMax() != 0) {
                queryString.append("&idle-max={idle-max}");
                parameters.add(Integer.toString(input.getIdleMax()));
            }
            if (input.getGranularity() != 0) {
                queryString.append("&granularity={granularity}");
                parameters.add(Integer.toString(input.getGranularity()));
            }
            // TODO add the rest of the parameters if they are interesting
        } catch (IOException e) {
            throw new IOError(e);
        }

        String[] params = parameters.toArray(new String[parameters.size()]);
        return post("/" + customerLogin + "/analytics/instrumentations?" + queryString.substring(1), null, Instrumentation.class, null, params).getValue();
    }

    public SimpleInstrumentationValue getInstrumentationValue(String customerLogin, String instrumentationId) throws InstantServersApiException {
        return get("/" + customerLogin + "/analytics/instrumentations/{instrumentationId}/value/raw", SimpleInstrumentationValue.class, instrumentationId).getValue();
    }

    public DecompositionInstrumentationValue getDecompositionInstrumentationValue(String customerLogin, String instrumentationId) throws InstantServersApiException {
        return get("/" + customerLogin + "/analytics/instrumentations/{instrumentationId}/value/raw", DecompositionInstrumentationValue.class, instrumentationId).getValue();
    }

    public void deleteInstrumentation(String customerLogin, String instrumentationId) throws InstantServersApiException {
        delete("/" + customerLogin + "/analytics/instrumentations/{instrumentationId}", instrumentationId);
    }
}
