/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

*/
package com.tdigital.instantservers.model.analytics;

import java.util.Map;


/*
{
  "value": { "headnode" : "33" },
  "transformations": {},
  "start_time": 1309383598,
  "duration": 1,
  "nsources": 1,
  "minreporting": 1,
  "requested_start_time": 1309383598,
  "requested_duration": 1,
  "requested_end_time": 1309383599
}
 */
public class DecompositionInstrumentationValue extends BaseInstrumentationValue {
    private Map<String, String> value;

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }

    /**
     * Helper method to solve the common case (obtain a single filtered value)
     * @param key
     * @return
     */
    public String getValue(String key) {
        return value.get(key);
    }
}
