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

import java.util.Date;

/**
    {
    "created_at":"2012-03-09T16:52:12+00:00",
    "updated_at":"2012-03-09T16:52:12+00:00",
    "key":"fqn",
    "value":"this.is.a.test.fqn.by.guido",
    "uri":"/zones/e6140a9e-2b25-4a37-a73a-a431a8e5a7f6/tags/fqn"
    }
 */
public class Tag {
    private Date createdAt;
    private Date updatedAt;
    private String key;
    private String value;

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
