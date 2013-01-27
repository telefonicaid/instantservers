/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.model.cloud;

import java.util.Date;

//{
//	"name": "nodejs",
//	"version": "1.1.3",
//	"os": "smartos",
//	"id": "7456f2b0-67ac-11e0-b5ec-832e6cf079d5",
//	"urn": "sdc:sdc:nodejs:1.1.3",
//	"default": true,
//	"type": "smartmachine",
//	"created": "2011-04-15T22:04:12+00:00"
//}
public class Dataset {
    private String name;
    private String version;
    private String os;
    private String id;
    private String urn;
    private boolean defaultPackage;
    private MachineType type;
    private Date created;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }
    public boolean isDefault() {
        return defaultPackage;
    }
    public void setDefault(boolean defaultPackage) {
        this.defaultPackage = defaultPackage;
    }
    public MachineType getType() {
        return type;
    }
    public void setType(MachineType type) {
        this.type = type;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
}
