package com.tdigital.instantservers.api.cloud;/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.model.Empty;
import com.tdigital.instantservers.model.cloud.Dataset;
import com.tdigital.instantservers.model.cloud.Key;
import com.tdigital.instantservers.model.cloud.Machine;
import com.tdigital.instantservers.model.cloud.MachineSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Cloud Public API.
 */
public class CloudAPIClient extends BaseAPIClient {

    public CloudAPIClient(String login, String password, String endpoint) {
        super(login, password, endpoint);
    }

    public Key[] listKeys() throws InstantServersApiException {
        return get("/my/keys", Key[].class).getValue();
    }

    public Key getKey(String name) throws InstantServersApiException {
        return get("/my/keys/{name}", Key.class, name).getValue();
    }

    public Key createKey(Key key) throws InstantServersApiException {
        return post("/my/keys", key, Key.class).getValue();
    }

    public void deleteKey(String name) throws InstantServersApiException {
        delete("/my/keys/{name}", name);
    }

    public Package[] listPackages() throws InstantServersApiException {
        return get("/my/packages", Package[].class).getValue();
    }

    public Package getPackage(String packageName) throws InstantServersApiException {
        return get("/my/packages/{package}", Package.class, packageName).getValue();
    }

    public Dataset[] listDatasets() throws InstantServersApiException {
        return get("/my/datasets", Dataset[].class).getValue();
    }

    public Dataset getDataset(String dataset) throws InstantServersApiException {
        return get("/my/datasets/{dataset}", Dataset.class, dataset).getValue();
    }

    public Machine createMachine(Machine data) throws InstantServersApiException {
        return post("/my/machines", data, Machine.class).getValue();
    }

    /*
     * The maximum allowable result set size is 1000
     */
    public Machine[] listMachines() throws InstantServersApiException {
        return get("/my/machines", Machine[].class).getValue();
    }

    public Machine[] listMachines(String filterKey, String filterValue) throws InstantServersApiException {
        return get("/my/machines?{key}={value}", Machine[].class, filterKey, filterValue).getValue();
    }

    public Machine getMachine(String id) throws InstantServersApiException {
        return get("/my/machines/{id}", Machine.class, id).getValue();
    }

    /*
     * Helper method
     */
    public Machine getMachineByName(String name) throws InstantServersApiException {
        return get("/my/machines?name={name}", Machine.class, name).getValue();
    }

    public void stopMachine(String id) throws InstantServersApiException {
        post("/my/machines/{id}?action=stop", id);
    }

    public void rebootMachine(String id) throws InstantServersApiException {
        post("/my/machines/{id}?action=reboot", id);
    }

    public void startMachine(String id) throws InstantServersApiException {
        post("/my/machines/{id}?action=start", id);
    }

    public void resizeMachine(String id, String packageName) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("package", packageName, formParams);

        post("/my/machines/{id}?action=resize", null, Empty.class, formParams, id);
    }

    public void deleteMachine(String id) throws InstantServersApiException {
        delete("/my/machines/{id}", id);
    }

    public MachineSnapshot createMachineSnapshot(String id, String name) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("name", name, formParams);

        return post("/my/machines/{id}/snapshots", null, MachineSnapshot.class, formParams, id).getValue();
    }

    public void startMachineFromSnapshot(String id, String snapshot) throws InstantServersApiException {
        post("/my/machines/{id}/snapshots/{name}", null, Empty.class, null, id, snapshot);
    }

    public void deleteMachineSnapshot(String id, String snapshot) throws InstantServersApiException {
        delete("/my/machines/{id}/snapshots/{name}", id, snapshot);
    }

    public MachineSnapshot getMachineSnapshot(String id, String name) throws InstantServersApiException {
        return get("/my/machines/{id}/snapshots/{name}", MachineSnapshot.class, id).getValue();
    }

    public MachineSnapshot[] listMachineSnapshots(String id) throws InstantServersApiException {
        return get("/my/machines/{id}/snapshots", MachineSnapshot[].class, id).getValue();
    }
}
