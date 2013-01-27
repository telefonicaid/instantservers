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

public class Nic {
    /**
     * MAC address, i.e. 90:b8:d0:f1:5a:3a
     */
    private String address;
    private String type; // "vnic", use enum when possible values are known
    private Date createdAt;
    private Date updatedAt;

    private NicTag[] nicTags;

    /**
     * IP address, i.e. 151.1.224.145
     */
    private String ip;
    private String interfaceName;
    private Network network;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
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
    public NicTag[] getNicTags() {
        return nicTags;
    }
    public void setNicTags(NicTag[] nicTags) {
        this.nicTags = nicTags;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    @JsonProperty("interface")
    public String getInterfaceName() {
        return interfaceName;
    }
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    public Network getNetwork() {
        return network;
    }
    public void setNetwork(Network network) {
        this.network = network;
    }
}
