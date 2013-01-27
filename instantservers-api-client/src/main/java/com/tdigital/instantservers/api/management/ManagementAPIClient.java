/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api.management;

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.HttpHeaders;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.api.InstantServersApiResponse;
import com.tdigital.instantservers.model.Empty;
import com.tdigital.instantservers.model.management.Dataset;
import com.tdigital.instantservers.model.management.Hostname;
import com.tdigital.instantservers.model.management.Nic;
import com.tdigital.instantservers.model.management.Package;
import com.tdigital.instantservers.model.management.ProvisionerMessage;
import com.tdigital.instantservers.model.management.Tag;
import com.tdigital.instantservers.model.management.Transition;
import com.tdigital.instantservers.model.management.VirtualMachine;
import com.tdigital.instantservers.model.management.Zone;
import com.tdigital.instantservers.model.management.ZoneSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MAPI.
 */
public class ManagementAPIClient extends BaseAPIClient {

    public ManagementAPIClient(String login, String password, String endpoint) {
        super(login, password, endpoint);
    }

    public Package[] listPackages() throws InstantServersApiException {
        Package[] list = get("/packages", Package[].class).getValue();
        return list == null ? new Package[0] : list;
    }

    public Package getPackage(String packageName) throws InstantServersApiException {
        return get("/packages/{package}", Package.class, packageName).getValue();
    }

    public Dataset[] listDatasets() throws InstantServersApiException {
        Dataset[] list = get("/datasets", Dataset[].class).getValue();
        return list == null ? new Dataset[0] : list;
    }

    public Dataset getDataset(String dataset) throws InstantServersApiException {
        return get("/datasets/{dataset}", Dataset.class, dataset).getValue();
    }

    public void tagZone(String id, String key, String value) throws InstantServersApiException {
        // ie /zones/uuid/tags?tag.foo=bar
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("tag." + key, value, formParams);

        post("/zones/{id}/tags", null, Tag[].class, formParams, id);
    }

    /**
     * Creates a Smart Machine. This method is not intended to create Virtual Machines
     * @param data
     * @return
     * @throws com.tdigital.instantservers.api.InstantServersApiException
     */
    public Transition createZone(Zone data) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("customer_uuid", data.getCustomerUuid(), formParams);
        tryAddParam("alias", data.getAlias(), formParams);
        tryAddParam("dataset_urn", data.getDatasetUrn(), formParams);
        tryAddParam("name", data.getName(), formParams);
        tryAddParam("networks", data.getNetworks(), formParams);
        tryAddParam("authorized_keys", data.getAuthorizedKeys(), formParams);

        if (data.getPackageName() != null) {
            tryAddParam("package", data.getPackageName(), formParams);
        } else {
            tryAddParam("ram", data.getRam(), formParams);
            tryAddParam("swap", data.getSwap(), formParams);
            tryAddParam("cpu_cap", data.getCpuCap(), formParams);
            tryAddParam("cpu_shares", data.getCpuShares(), formParams);
            tryAddParam("zfs_io_priority", data.getZfsIoPriority(), formParams);
            tryAddParam("lightweight_processes", data.getLightweightProcesses(), formParams);
            tryAddParam("disk", data.getDisk(), formParams);
        }

        InstantServersApiResponse<Empty> response = post("/zones", formParams);
        if (response.isSuccess()) {
            String transitionUri = response.getHeader(HttpHeaders.X_JOYENT_TRANSITION_URI);
            Transition result = get(transitionUri, Transition.class).getValue();
            result.setUri(transitionUri);
            return result;
        }

        return null;
    }

    /*
     * The maximum allowable result set size is 1000
     */
    public Zone[] listZones() throws InstantServersApiException {
        Zone[] list = get("/zones", Zone[].class).getValue();
        return list == null ? new Zone[0] : list;
    }

    public Zone[] listZones(String customerUUID) throws InstantServersApiException {
        Zone[] list = get("/zones?owner_uuid={value}", Zone[].class, customerUUID).getValue();
        return list == null ? new Zone[0] : list;
    }

    public Zone[] searchZones(String filterKey, String filterValue) throws InstantServersApiException {
        Zone[] list = get("/search/zones?{key}={value}", Zone[].class, filterKey, filterValue).getValue();
        return list == null ? new Zone[0] : list;
    }

    public Zone getZone(String id) throws InstantServersApiException {
        return get("/zones/{id}", Zone.class, id).getValue();
    }

    /**
     * Helper method, the name is the container alias. It is recommended to use
     * getZone when possible because this methods makes two HTTP requests (one to
     * search by alias and the second one to include extended information about
     * the IPs, etc)
     */
    public Zone getZoneByAlias(String alias) throws InstantServersApiException {
        Zone result = null;
        Zone[] searchResults = get("/search/zones?alias={alias}", Zone[].class, alias).getValue();
        if (searchResults != null) {
            for (Zone zone : searchResults) {
                // double check the alias because the search returns any machine "like %alias%"
                if (zone.getAlias().equals(alias)) {
                    result = getZone(zone.getName());
                    break;
                }
            }
        }

        return result;
    }

    public void stopZone(String id) throws InstantServersApiException {
        post("/zones/{id}/shutdown", null, Empty.class, null, id);
    }

    public void rebootZone(String id) throws InstantServersApiException {
        post("/zones/{id}/reboot", null, Empty.class, null, id);
    }

    public void startZone(String id) throws InstantServersApiException {
        post("/zones/{id}/startup", null, Empty.class, null, id);
    }

    public void resizeZone(String id, String packageName) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("package", packageName, formParams);

        post("/zones/{id}/resize", null, Empty.class, formParams, id);
    }

    public void deleteZone(String id) throws InstantServersApiException {
        deleteZone(id, false);
    }

    public void deleteZone(String id, boolean force) throws InstantServersApiException {
        if (force) {
            Map<String, Object> formParams = new HashMap<String, Object>();
            tryAddParam("force", "true", formParams); // Set true to destroy a zone even if its running
            delete("/zones/{id}", formParams, id);
        } else {
            delete("/zones/{id}", id);
        }

    }

    public ZoneSnapshot createZoneSnapshot(String id, String name) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("snapshot_name", name, formParams);

        // documentation includes a name parameter, if needed
        // http://apidocs.joyent.com/sdcapidoc/mapi/#POST-/zones/:zone_id/zfs_snapshots

        return post("/zones/{id}/zfs_snapshots", null, ZoneSnapshot.class, formParams, id).getValue();
    }

    public void startZoneFromSnapshot(String id, String snapshot) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("zfs_snapshot", snapshot, formParams);

        post("/zones/{id}/rollback", null, Empty.class, formParams, id);
    }

    public void deleteZoneSnapshot(String id, String snapshot) throws InstantServersApiException {
        delete("/zones/{id}/zfs_snapshots/{name}", id, snapshot);
    }

    public ZoneSnapshot getZoneSnapshot(String id, String name) throws InstantServersApiException {
        return get("/zones/{id}/zfs_snapshots/{name}", ZoneSnapshot.class, id, name).getValue();
    }

    public ZoneSnapshot[] listZoneSnapshots(String id) throws InstantServersApiException {
        return get("/zones/{id}/zfs_snapshots", ZoneSnapshot[].class, id).getValue();
    }

    public Nic[] getZoneNics(String id) throws InstantServersApiException {
        return get("/zones/{id}/nics", Nic[].class, id).getValue();
    }

    public Transition getZoneTransition(String id) throws InstantServersApiException {
        Transition result = get("/zones/{id}/transition", Transition.class, id).getValue();
        result.setUri("/zones/" + id + "/transition");
        return result;
    }

    public Hostname getHostnames(String name) throws InstantServersApiException {
        return get("/hostnames/{name}", Hostname.class, name).getValue();
    }

    public void tagVirtualMachine(String id, String key, String value) throws InstantServersApiException {
        // ie /zones/uuid/tags?tag.foo=bar
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("tag." + key, value, formParams);

        post("/vms/{id}/tags", null, Tag[].class, formParams, id);
    }

    /**
     * Creates a Smart Machine. This method is not intended to create Virtual Machines
     * @param data
     * @return
     * @throws com.tdigital.instantservers.api.InstantServersApiException
     */
    public Transition createVirtualMachine(VirtualMachine data) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("owner_uuid", data.getCustomerUuid(), formParams);
        tryAddParam("alias", data.getAlias(), formParams);
        tryAddParam("dataset_urn", data.getDatasetUrn(), formParams);
        tryAddParam("alias", data.getName(), formParams);
        tryAddParam("networks", data.getNetworks(), formParams);
        tryAddParam("root_authorized_keys", data.getAuthorizedKeys(), formParams);

        if (data.getPackageName() != null) {
            tryAddParam("package", data.getPackageName(), formParams);
        } else {
            tryAddParam("memory", data.getRam(), formParams);
            tryAddParam("cpu_cap", data.getCpuCap(), formParams);
            tryAddParam("cpu_shares", data.getCpuShares(), formParams);
            tryAddParam("lightweight_processes", data.getLightweightProcesses(), formParams);
            tryAddParam("storage", data.getDisk(), formParams);
        }

        InstantServersApiResponse<Empty> response = post("/vms", formParams);
        if (response.isSuccess()) {
            String transitionUri = response.getHeader(HttpHeaders.X_JOYENT_TRANSITION_URI);
            Transition result = get(transitionUri, Transition.class).getValue();
            result.setUri(transitionUri);
            return result;
        }

        return null;
    }

    /*
     * The maximum allowable result set size is 1000
     */
    public VirtualMachine[] listVirtualMachines() throws InstantServersApiException {
        VirtualMachine[] list = get("/vms", VirtualMachine[].class).getValue();
        return list == null ? new VirtualMachine[0] : list;
    }

    public VirtualMachine[] listVirtualMachines(String customerUUID) throws InstantServersApiException {
        VirtualMachine[] list = get("/vms?owner_uuid={value}", VirtualMachine[].class, customerUUID).getValue();
        return list == null ? new VirtualMachine[0] : list;
    }

    public VirtualMachine getVirtualMachine(String id) throws InstantServersApiException {
        return get("/vms/{id}", VirtualMachine.class, id).getValue();
    }

    public void stopVirtualMachine(String id) throws InstantServersApiException {
        post("/vms/{id}/shutdown", null, Empty.class, null, id);
    }

    public void rebootVirtualMachine(String id) throws InstantServersApiException {
        post("/vms/{id}/reboot", null, Empty.class, null, id);
    }

    public void startVirtualMachine(String id) throws InstantServersApiException {
        post("/vms/{id}/startup", null, Empty.class, null, id);
    }

    public void haltVirtualMachine(String id) throws InstantServersApiException {
        post("/vms/{id}/halt", null, Empty.class, null, id);
    }

    public void deleteVirtualMachine(String id) throws InstantServersApiException {
        deleteVirtualMachine(id, false);
    }

    public void deleteVirtualMachine(String id, boolean force) throws InstantServersApiException {
        if (force) {
            Map<String, Object> formParams = new HashMap<String, Object>();
            tryAddParam("force", "true", formParams); // Set true to destroy a zone even if its running
            delete("/vms/{id}", formParams, id);
        } else {
            delete("/vms/{id}", id);
        }
    }

    public Nic[] getVirtualMachineNics(String id) throws InstantServersApiException {
        return get("/vms/{id}/nics", Nic[].class, id).getValue();
    }

    public Transition getVirtualMachineTransition(String id) throws InstantServersApiException {
        Transition result = get("/vms/{id}/transition", Transition.class, id).getValue();
        result.setUri("/vms/" + id + "/transition");
        return result;
    }

    public List<ProvisionerMessage> getProvisionerMessages(Integer offset, Integer limit) throws InstantServersApiException {

        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("offset", offset, formParams);
        tryAddParam("limit", limit, formParams);

        ProvisionerMessage[] messages =
                get("/provisioner_messages", ProvisionerMessage[].class, formParams).getValue();
        return messages == null ? new ArrayList<ProvisionerMessage>() : Arrays.asList(messages);
    }

    public ProvisionerMessage getProvisionerMessages(String messageId) throws InstantServersApiException {
        return get("/provisioner_messages/{id}", ProvisionerMessage.class, messageId).getValue();
    }

    public List<ProvisionerMessage> getProvisionerMessages(
            String customerUUID, String zoneId, Integer offset, Integer limit) throws InstantServersApiException {

        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("offset", offset, formParams);
        tryAddParam("limit", limit, formParams);

        ProvisionerMessage[] messages = get("/customers/{owner_uuid}/zones/{zone_id}/provisioner_messages",
                ProvisionerMessage[].class, formParams, customerUUID, zoneId).getValue();

        return messages == null ? new ArrayList<ProvisionerMessage>() : Arrays.asList(messages);
    }

    public List<ProvisionerMessage> getProvisionerMessages(String customerUUID, String zoneId, String messageId)
            throws InstantServersApiException {
        ProvisionerMessage[] messages = get("/customers/{owner_uuid}/zones/{zone_id}/provisioner_messages/{id}",
                ProvisionerMessage[].class, customerUUID, zoneId, messageId).getValue();
        return messages == null ? new ArrayList<ProvisionerMessage>() : Arrays.asList(messages);
    }

    /**
     * This method call a hidden service of joyent, that returns a virtual machine provisioner messages.
     * @param zoneId
     * @return
     * @throws com.tdigital.instantservers.api.InstantServersApiException
     */
    public List<ProvisionerMessage> getProvisionerMessagesVms(String zoneId) throws InstantServersApiException {
        return getProvisionerMessagesVms(zoneId, null, null);
    }

    public List<ProvisionerMessage> getProvisionerMessagesVms(String zoneId, Integer offset, Integer limit) throws InstantServersApiException {

        Map<String, Object> params = new HashMap<String, Object>();
        tryAddParam("offset", offset, params);
        tryAddParam("limit", limit, params);

        ProvisionerMessage[] messages = get("/vms/{zone_id}/provisioner_messages",
                ProvisionerMessage[].class, params, zoneId).getValue();

        return messages == null ? new ArrayList<ProvisionerMessage>() : Arrays.asList(messages);

    }
}
