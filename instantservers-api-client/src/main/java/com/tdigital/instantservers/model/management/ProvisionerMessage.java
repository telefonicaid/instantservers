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
import java.util.Map;

/**
 * This class representa a provisioner message. Many fields have been ommited for simplicity.
 * For a complete example see {@link https://gist.github.com/4657b4f60b4f298faafe}
 */
public class ProvisionerMessage {

    private static final String CREATE_ANY = "machine_create";

    private static final String RESIZE_SM = "zone_resize";
    private static final String DELETE_SM = "zone_destroy";
    private static final String DELETE_VM = "hvm_destroy";

    private static final String SUCCESS_STATE = "success";

    private int id;
    private String methodName; // machine_create, zone_resize, zone_destroy/hvm_destroy, zone_shutdown
    private Date createdAt;
    private String provisionableType; // Zone, VM
    private String state;
    private String uuid;
    private String uri;

    /**
     * Generic parameters. They are only present in some provisioner message types, and can
     * contain a great variety of different key/value pairs. This is why it is modelled as
     * a map.
     */
    private Map<String, Object> parameters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProvisionableType() {
        return provisionableType;
    }

    public void setProvisionableType(String provisionableType) {
        this.provisionableType = provisionableType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public boolean isCreation() {
        return CREATE_ANY.equalsIgnoreCase(methodName);
    }

    public boolean isSuccess() {
        return SUCCESS_STATE.equalsIgnoreCase(state);
    }

    public boolean isResize() {
        return RESIZE_SM.equalsIgnoreCase(methodName);
    }

    public boolean isDeletion() {
        return DELETE_VM.equalsIgnoreCase(methodName) || DELETE_SM.equalsIgnoreCase(methodName);
    }

}
