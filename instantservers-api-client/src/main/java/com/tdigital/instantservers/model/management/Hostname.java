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

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

// {
//    "id":3,
//    "name":"guido-mapi",
//    "created_at":"2012-02-09T22:33:47+00:00",
//    "updated_at":"2012-02-09T22:33:47+00:00",
//    "addressable_type":"Zone",
//    "addressable_id":6
// }
public class Hostname {
    private int id;

    private String name;

    @JsonProperty("created_at")
    private Date created;

    @JsonProperty("updated_at")
    private Date updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
