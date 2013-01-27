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

import java.util.Date;

/**
{
    "fingerprint": "13:48:5a:df:04:ab:1c:3b:17:f9:45:32:e1:f0:9c:e1",
    "customer_id": 3,
    "customer_uuid": "c6c34bac-684f-47bd-9c20-26e4974618d1",
    "updated_at": "2011-10-10T01:55:35+00:00",
    "created_at": "2011-10-10T01:55:35+00:00",
    "name": "butter",
    "body": "ssh-rsa AAAAB3NzaC1...tvw== Tommy's Key",
    "id": 3,
    "standard": "-----BEGIN PUBLIC KEY-----\nMI...Iw==\n-----END PUBLIC KEY-----\n"
}
 */
public class CustomerKey {
    private int id;
    private String fingerprint;
    private int customerId;
    private String customerUuid;
    private Date updatedAt;
    private Date createdAt;
    private String name;
    private String body;
    private String standard;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFingerprint() {
        return fingerprint;
    }
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerUuid() {
        return customerUuid;
    }
    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getStandard() {
        return standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
}
