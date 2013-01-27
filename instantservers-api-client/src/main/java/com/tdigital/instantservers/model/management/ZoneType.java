/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.model.management;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ZoneType {
    virtualmachine("zvol"),
    smartmachine("zone-dataset");

    private String virtualSystemType;

    private static final Map<String, ZoneType> REVERSE_LOOKUP = new HashMap<String, ZoneType>();

    static {
        for(ZoneType zoneType : EnumSet.allOf(ZoneType.class)) {
            REVERSE_LOOKUP.put(zoneType.getVirtualSystemType(), zoneType);
        }
    }

    private ZoneType(String virtualSystemType) {
        this.virtualSystemType = virtualSystemType;
    }

    public String getVirtualSystemType() {
        return virtualSystemType;
    }

    public static ZoneType find(String virtualSystemType) {
        return REVERSE_LOOKUP.get(virtualSystemType);
    }
}
