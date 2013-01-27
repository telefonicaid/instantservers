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

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
{
    "id":1,
    "name":"20120221181533",
    "zfs_storage_pool_id":2,
    "zone_id":36,
    "creation_state":"success",
    "disk_used_in_gigabytes":0.0,
    "disk_referenced_in_gigabytes":0.0,
    "created_at":"2012-02-21T18:15:33+00:00",
    "updated_at":"2012-02-21T18:15:38+00:00",
    "destroyed_at":null,
    "uri":"/zones/GUIDO.GUIDO.JOYENT.JOYWEB/zfs_snapshots/1"
}
 */
public class ZoneSnapshot {
    private String id;
    private String name;
    private ZoneSnapshotState state;
    private Date createdAt;
    private Date updatedAt;
    private Date destroyedAt;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ZoneSnapshotState getState() {
        return state;
    }
    public void setState(ZoneSnapshotState state) {
        this.state = state;
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
    public Date getDestroyedAt() {
        return destroyedAt;
    }
    public void setDestroyedAt(Date destroyedAt) {
        this.destroyedAt = destroyedAt;
    }
    /*
     * Helper method
     */
    @JsonIgnore
    public String getDescription() {
        return "snapshot " + name + " " + createdAt;
    }
}
