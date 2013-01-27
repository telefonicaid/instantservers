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
import org.codehaus.jackson.annotate.JsonProperty;

/**
customer_uuid   String  required    Unique customer ID
alias   String  optional    An alias by which a zone is known in the future
package String  optional    Package name to use when provisioning
dataset_uuid    String  optional    UUID of dataset to use for the zone.
dataset_urn String  optional    URN of dataset to use for the zone.
dataset String  optional    Name of dataset zone will originate from
authorized_keys String  optional    Public keys to be added to the zone
networks    String  optional    Comma separated names of logical networks or logical network pools assigned to this zone
server_uuid String  optional    UUID of server on which the zone will be provisioned
ram Integer optional    RAM in megabytes assigned to the zone
swap    Integer optional    Swap in megabytes assigned to the zone
disk    Integer optional    Disk in megabytes assignt to the zone
lightweight_processes   Integer optional    Maximum lightweight processes
cpu_shares  Integer optional    Relative proportion of a servers cores
cpu_cap Integer optional    Maximum cores a zone can use. 100 is one
zfs_io_priority Integer optional    Relative proportion of disk IO for throttling purposes
customer_metadata   JSON    optional    Hash where a customer can store arbitrary metadata
internal_metadata   JSON    optional    Hash where the operator can store arbitrary metadata
callback    String  optional    URL to POST to when the provision is complete
zpool   String  optional    Name of Zpool on Server which Zone should be provisioned
force   String  optional    Force the provisioning of a zone to a server
 */
public class Zone {
    private String customerUuid;
    private String alias;
    private String name; // UUID
    private String packageName;
    private String datasetUrn;
    private ZoneState runningStatus;
    private int ram; // MB
    private int swap; // MB
    private int disk; // MB

    /**
     * Relative proportion of a servers cores
     */
    private int cpuShares;

    /**
     * Maximum cores a zone can use. 100 is one
     */
    private int cpuCap;
    private int zfsIoPriority;
    private int lightweightProcesses;
    private String authorizedKeys;

    private Ip[] ips;

    /**
     * Comma separated names of logical networks or logical network pools assigned to this zone
     */
    private String networks;

    private Credential[] credentials;

    public String getNetworks() {
        return networks;
    }
    public void setNetworks(String networks) {
        this.networks = networks;
    }
    public String getCustomerUuid() {
        return customerUuid;
    }
    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    @JsonProperty("package")
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public String getDatasetUrn() {
        return datasetUrn;
    }
    public void setDatasetUrn(String datasetUrn) {
        this.datasetUrn = datasetUrn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ZoneState getRunningStatus() {
        return runningStatus;
    }
    public void setRunningStatus(ZoneState runningStatus) {
        this.runningStatus = runningStatus;
    }
    public int getRam() {
        return ram;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
    public int getSwap() {
        return swap;
    }
    public void setSwap(int swap) {
        this.swap = swap;
    }
    public int getDisk() {
        return disk;
    }
    public void setDisk(int disk) {
        this.disk = disk;
    }
    public int getCpuShares() {
        return cpuShares;
    }
    public void setCpuShares(int cpuShares) {
        this.cpuShares = cpuShares;
    }
    public Ip[] getIps() {
        return ips;
    }
    public void setIps(Ip[] ips) {
        this.ips = ips;
    }
    public int getCpuCap() {
        return cpuCap;
    }
    public void setCpuCap(int cpuCap) {
        this.cpuCap = cpuCap;
    }
    public int getZfsIoPriority() {
        return zfsIoPriority;
    }
    public void setZfsIoPriority(int zfsIoPriority) {
        this.zfsIoPriority = zfsIoPriority;
    }
    public int getLightweightProcesses() {
        return lightweightProcesses;
    }
    public void setLightweightProcesses(int lightweightProcesses) {
        this.lightweightProcesses = lightweightProcesses;
    }
    public String getAuthorizedKeys() {
        return authorizedKeys;
    }
    public void setAuthorizedKeys(String authorizedKeys) {
        this.authorizedKeys = authorizedKeys;
    }

    public boolean isPoweredOn() {
        return this.getRunningStatus() == ZoneState.running || this.getRunningStatus() == ZoneState.installed;
    }

    @JsonIgnore
    public boolean isVirtualMachine() {
        return false;
    }
    @JsonIgnore
    public Credential[] getCredentials() {
        return credentials;
    }
    public void setCredentials(Credential[] credentials) {
        this.credentials = credentials;
    }
}
