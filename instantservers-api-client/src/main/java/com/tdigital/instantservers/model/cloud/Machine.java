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
import java.util.Map;

/**
 *

 {
  "id": "55a366ce-6c30-4f88-a36b-53638bd0cb62",
  "name": abcd1234",
  "type": "smartmachine",
  "state": "provisioning",
  "dataset": nodejs-1.1.4",
  "memory": 128,
  "disk": 5120,
  "ips": [
    "10.88.88.51"
  ],
  "metadata": {},
  "created": "2011-06-03T00:02:31+00:00",
  "updated": "2011-06-03T00:02:31+00:00",
}

 * @author guido
 *
 */
public class Machine {
    private String id;
    private String name;
    private MachineType type;
    private MachineState state;
    private String dataset;
    private String packageName;
    private int memory;
    private int disk;
    private String[] ips;
    private Map<String, String> metadata;
    private Date created;
    private Date updated;

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
    public MachineType getType() {
        return type;
    }
    public void setType(MachineType type) {
        this.type = type;
    }
    public MachineState getState() {
        return state;
    }
    public void setState(MachineState state) {
        this.state = state;
    }
    public String getDataset() {
        return dataset;
    }
    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
    public String getPackage() {
        return packageName;
    }
    public void setPackage(String packageName) {
        this.packageName = packageName;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }
    public int getDisk() {
        return disk;
    }
    public void setDisk(int disk) {
        this.disk = disk;
    }
    public String[] getIps() {
        return ips;
    }
    public void setIps(String[] ips) {
        this.ips = ips;
    }
    public Map<String, String> getMetadata() {
        return metadata;
    }
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
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
