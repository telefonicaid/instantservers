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
 {"name":"external","description":"151.1.224.128/26","vlan_id":102,"admin":false,"created_at":"2012-02-09T13:53:02+00:00","updated_at":"2012-02-09T13:53:02+00:00","uri":"/networks/2","start_ip":"151.1.224.140","end_ip":"151.1.224.190",
 "default_gateway_ip":"151.1.224.129","subnet":"151.1.224.128/26","nic_tag":"/nic_tags/2","resolver_ips":["8.8.4.4","8.8.8.8"]}
 */
public class Network {
    private String name;
    private String description;
    private int vlanId;
    private boolean admin;
    private Date createdAt;
    private Date updatedAt;
    private String startIp;
    private String endIp;
    private String defaultGatewayIp;
    private String subnet;
    private String[] resolverIps;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getVlanId() {
        return vlanId;
    }
    public void setVlanId(int vlanId) {
        this.vlanId = vlanId;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
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
    public String getStartIp() {
        return startIp;
    }
    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }
    public String getEndIp() {
        return endIp;
    }
    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }
    public String getDefaultGatewayIp() {
        return defaultGatewayIp;
    }
    public void setDefaultGatewayIp(String defaultGatewayIp) {
        this.defaultGatewayIp = defaultGatewayIp;
    }
    public String getSubnet() {
        return subnet;
    }
    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }
    public String[] getResolverIps() {
        return resolverIps;
    }
    public void setResolverIps(String[] resolverIps) {
        this.resolverIps = resolverIps;
    }
}
