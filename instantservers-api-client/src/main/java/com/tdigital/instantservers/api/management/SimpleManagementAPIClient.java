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

import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.model.management.Nic;
import com.tdigital.instantservers.model.management.Transition;
import com.tdigital.instantservers.model.management.VirtualMachine;
import com.tdigital.instantservers.model.management.Zone;
import com.tdigital.instantservers.model.management.ZoneSnapshot;
import com.tdigital.instantservers.model.management.ZoneType;

/**
 * MAPI extension that takes into account the kind of machine (virtual machine or
 * smart machine) we are creating to invoke the appropriate endpoint.
 */
public class SimpleManagementAPIClient extends ManagementAPIClient {
    public SimpleManagementAPIClient(String login, String password, String endpoint) {
        super(login, password, endpoint);
    }

    /**
     * Creates a Smart Machine. This method is not intended to create Virtual Machines
     * @param data
     * @return
     * @throws com.tdigital.instantservers.api.InstantServersApiException
     */
    public Transition create(Zone data) throws InstantServersApiException {
        Transition result = null;
        if (data.isVirtualMachine()) {
            result = super.createVirtualMachine((VirtualMachine) data);
        } else {
            result = super.createZone(data);
        }

        return result;
    }

    /**
     * Looks for all kind of zones (smart machines + virtual machines)
     * The maximum allowable result set size is 1000
     */
    public Zone[] list() throws InstantServersApiException {
        Zone[] smartMachines = super.listZones();
        VirtualMachine[] virtualMachines = super.listVirtualMachines();

        return mergeZoneArrays(smartMachines, virtualMachines);
    }

    /**
     * Looks for all kind of zones (smart machines + virtual machines) owned by a customer
     * The maximum allowable result set size is 1000
     * 
     * @param customerUUID Customer identifier
     */
    public Zone[] list(String customerUUID) throws InstantServersApiException {
        Zone[] smartMachines = super.listZones(customerUUID);
        VirtualMachine[] virtualMachines = super.listVirtualMachines(customerUUID);

        return mergeZoneArrays(smartMachines, virtualMachines);
    }

    /**
     * Efficient way to return a merged array with the element of both arrays.
     * If one of the inputs is null or empty, the other array is returned, without copying it
     * @param smartMachines One input array
     * @param virtualMachines Other input array
     * @return A array with both input arrays merged
     */
    private Zone[] mergeZoneArrays(Zone[] smartMachines, Zone[] virtualMachines) {
        if (smartMachines == null || smartMachines.length == 0) {
            if (virtualMachines == null || virtualMachines.length == 0) {
                // None has data, return an empty array
                return new Zone[0];
            } else {
                // Only virtualMachines has data
                return virtualMachines;
            }
        } else {
            if (virtualMachines == null || virtualMachines.length == 0) {
                // Only smarMachines has data
                return smartMachines;
            } else {
                // Both arrays have data, merge them
                Zone[] merged = new Zone[smartMachines.length + virtualMachines.length];
                System.arraycopy(smartMachines, 0, merged, 0, smartMachines.length);
                System.arraycopy(virtualMachines, 0, merged, smartMachines.length, virtualMachines.length);

                return merged;
            }
        }
    }

    public Zone getEntity(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.getZone(id);
        case virtualmachine:
            return super.getVirtualMachine(id);
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    /**
     * Helper method, the name is the container alias. It is recommended to use
     * getZone when possible because this methods makes two HTTP requests (one to
     * search by alias and the second one to include extended information about
     * the IPs, etc)
     */
    public Zone getByAlias(String alias, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.getZoneByAlias(alias);
        case virtualmachine:
            // TODO find a method to search virtualmachines by alias
            VirtualMachine[] virtualmachines = super.listVirtualMachines();
            for (VirtualMachine virtualmachine : virtualmachines) {
                if (alias.equals(virtualmachine.getAlias())) {
                    return virtualmachine;
                }
            }
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void stop(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.stopZone(id);
            break;
        case virtualmachine:
            super.stopVirtualMachine(id);
            break;
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void reboot(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.rebootZone(id);
            break;
        case virtualmachine:
            super.rebootVirtualMachine(id);
            break;
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void start(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.startZone(id);
            break;
        case virtualmachine:
            super.startVirtualMachine(id);
            break;
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void resize(String id, String packageName, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.resizeZone(id, packageName);
            break;
        case virtualmachine:
            throw new UnsupportedOperationException("This method is not supported for Virtual Machines");
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void remove(String id, ZoneType type) throws InstantServersApiException {
        remove(id, type, false); // do not force removal by default
    }

    public void remove(String id, ZoneType type, boolean force) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.deleteZone(id, force);
            break;
        case virtualmachine:
            super.deleteVirtualMachine(id, force);
            break;
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void tag(String id, ZoneType type, String key, String value) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.tagZone(id, key, value);
            break;
        case virtualmachine:
            super.tagVirtualMachine(id, key, value);
            break;
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public ZoneSnapshot createSnapshot(String id, String name, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.createZoneSnapshot(id, name);
        case virtualmachine:
            throw new UnsupportedOperationException("This method is not supported for Virtual Machines");
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void startFromSnapshot(String id, String snapshot, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.startZoneFromSnapshot(id, snapshot);
            break;
        case virtualmachine:
            throw new UnsupportedOperationException("This method is not supported for Virtual Machines");
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public void deleteSnapshot(String id, String snapshot, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            super.deleteZoneSnapshot(id, snapshot);
            break;
        case virtualmachine:
            throw new UnsupportedOperationException("This method is not supported for Virtual Machines");
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public ZoneSnapshot getSnapshot(String id, String name, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.getZoneSnapshot(id, name);
        case virtualmachine:
            throw new UnsupportedOperationException("This method is not supported for Virtual Machines");
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public ZoneSnapshot[] listSnapshots(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.listZoneSnapshots(id);
        case virtualmachine:
            return new ZoneSnapshot[0]; // empty array on purpose
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public Nic[] getNics(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.getZoneNics(id);
        case virtualmachine:
            return super.getVirtualMachineNics(id);
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }

    public Transition getTransition(String id, ZoneType type) throws InstantServersApiException {
        switch (type) {
        case smartmachine:
            return super.getZoneTransition(id);
        case virtualmachine:
            return super.getVirtualMachineTransition(id);
        default:
            throw new IllegalArgumentException("Not valid zone type");
        }
    }
}
