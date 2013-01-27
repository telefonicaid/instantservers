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

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

//{
//    "name": "just-booted",
//    "state": "queued",
//    "created": "2011-07-05T17:19:26+00:00",
//    "updated": "2011-07-05T17:19:26+00:00"
//}
public class MachineSnapshot {
    private String name;
    private MachineSnapshotState state;
    private Date created;
    private Date updated;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MachineSnapshotState getState() {
        return state;
    }
    public void setState(MachineSnapshotState state) {
        this.state = state;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /*
     * Helper method
     */
    @JsonIgnore
    public String getDescription() {
        return "snapshot " + name + " " + created;
    }
}
