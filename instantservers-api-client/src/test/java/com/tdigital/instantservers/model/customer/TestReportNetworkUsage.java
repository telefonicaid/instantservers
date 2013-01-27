/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.model.customer;

import com.tdigital.instantservers.model.billing.Report;
import com.tdigital.instantservers.utils.JSONUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestReportNetworkUsage {

    @Test
    public void shouldReportNetworkUsage() throws IOException {
        String json = "{" +
                "\"38f18ff9-b17a-498c-bab4-24a778e9c477\": {" +
                "   \"metering\": {" +
                "       \"network\": {" +
                "           \"net0\": {" +
                "               \"bytes_received_delta\" : 1000, \"bytes_sent_delta\" : 2000" +
                "           }," +
                "           \"net1\": {" +
                "               \"bytes_received_delta\" : 3000, \"bytes_sent_delta\" : 4000" +
                "           }" +
                "       } " +
                "   } " +
                "}," +
                "\"6854af30-30f9-488c-ae5f-7efd39c407e7\": {" +
                "   \"metering\": {" +
                "       \"network\": {" +
                "           \"net0\": {" +
                "               \"bytes_received_delta\" : 1, \"bytes_sent_delta\" : 2" +
                "           }," +
                "           \"net1\": {" +
                "               \"bytes_received_delta\" : 3, \"bytes_sent_delta\" : 4" +
                "           }" +
                "       } " +
                "   } " +
                "}" +
                "}";

        Report report = JSONUtils.deserialize(json, Report.class);
        Assert.assertEquals(10000, report.getNetworkConsumption("38f18ff9-b17a-498c-bab4-24a778e9c477"));
    }
}
