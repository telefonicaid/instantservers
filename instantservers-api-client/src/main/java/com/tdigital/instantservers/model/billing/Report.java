/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.model.billing;

import org.apache.commons.jxpath.JXPathContext;

import java.util.HashMap;
import java.util.Map;

/**
{
  "38f18ff9-b17a-498c-bab4-24a778e9c477": {
    "configuration": {
      "history": [
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl1",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.13",
                "mac_addr": "90:b8:d0:5a:bd:5c",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "snapshot_usage": "0",
            "status": "running",
            "zfs_io_priority": "1",
            "zfs_quota": "5368709120",
            "zone": "38f18ff9-b17a-498c-bab4-24a778e9c477",
            "zone_uuid": "38f18ff9-b17a-498c-bab4-24a778e9c477"
          },
          "period_end": "2011-11-09T16:00:00.002Z",
          "period_start": "2011-11-09T15:59:57.710Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl1",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.13",
                "mac_addr": "90:b8:d0:5a:bd:5c",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "snapshot_usage": "0",
            "status": "running",
            "zfs_io_priority": "1",
            "zfs_quota": "5368709120",
            "zone": "38f18ff9-b17a-498c-bab4-24a778e9c477",
            "zone_uuid": "38f18ff9-b17a-498c-bab4-24a778e9c477"
          },
          "period_end": "2011-11-08T22:00:00.001Z",
          "period_start": "2011-11-08T21:33:27.214Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl1",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.13",
                "mac_addr": "90:b8:d0:5a:bd:5c",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "status": "running",
            "zfs_io_priority": "1",
            "zone": "38f18ff9-b17a-498c-bab4-24a778e9c477",
            "zone_uuid": "38f18ff9-b17a-498c-bab4-24a778e9c477"
          },
          "period_end": "2011-11-09T17:59:52.189Z",
          "period_start": "2011-11-09T16:00:00.002Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl1",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.13",
                "mac_addr": "90:b8:d0:5a:bd:5c",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "status": "running",
            "zfs_io_priority": "1",
            "zone": "38f18ff9-b17a-498c-bab4-24a778e9c477",
            "zone_uuid": "38f18ff9-b17a-498c-bab4-24a778e9c477"
          },
          "period_end": "2011-11-08T23:59:53.645Z",
          "period_start": "2011-11-08T22:00:00.001Z"
        }
      ]
    },
    "metering": {
      "network": {
        "net0": {
          "bytes_received_delta": 313994,
          "bytes_sent_delta": 3150,
          "period_end": "2011-11-09T18:24:50.958Z",
          "period_start": "2011-11-08T21:33:29.164Z"
        }
      }
    },
    "owner_uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
    "zone_uuid": "38f18ff9-b17a-498c-bab4-24a778e9c477"
  },
  "6854af30-30f9-488c-ae5f-7efd39c407e7": {
    "configuration": {
      "history": [
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl2",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.14",
                "mac_addr": "90:b8:d0:68:e6:3d",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "snapshot_usage": "0",
            "status": "running",
            "zfs_io_priority": "1",
            "zfs_quota": "5368709120",
            "zone": "6854af30-30f9-488c-ae5f-7efd39c407e7",
            "zone_uuid": "6854af30-30f9-488c-ae5f-7efd39c407e7"
          },
          "period_end": "2011-11-08T22:00:00.001Z",
          "period_start": "2011-11-08T21:33:27.214Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl2",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.14",
                "mac_addr": "90:b8:d0:68:e6:3d",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "snapshot_usage": "0",
            "status": "running",
            "zfs_io_priority": "1",
            "zfs_quota": "5368709120",
            "zone": "6854af30-30f9-488c-ae5f-7efd39c407e7",
            "zone_uuid": "6854af30-30f9-488c-ae5f-7efd39c407e7"
          },
          "period_end": "2011-11-08T23:59:53.645Z",
          "period_start": "2011-11-08T22:00:00.001Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl2",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.14",
                "mac_addr": "90:b8:d0:68:e6:3d",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "status": "running",
            "zfs_io_priority": "1",
            "zone": "6854af30-30f9-488c-ae5f-7efd39c407e7",
            "zone_uuid": "6854af30-30f9-488c-ae5f-7efd39c407e7"
          },
          "period_end": "2011-11-09T17:59:52.189Z",
          "period_start": "2011-11-09T16:00:00.002Z"
        },
        {
          "configuration": {
            "attributes": {
              "billing-id": "47e6af92-daf0-11e0-ac11-473ca1173ab0",
              "default-gateway": "10.88.88.2",
              "dns-domain": "local",
              "hostname": "jl2",
              "owner-uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
              "package-name": "regular_128",
              "package-version": "1.0.0",
              "resolvers": "8.8.8.8,8.8.4.4",
              "tmpfs": "128"
            },
            "brand": "joyent",
            "cpu_cap": "350",
            "cpu_shares": "128",
            "max_locked_mem": "134217728",
            "max_lwps": "2000",
            "max_physical_mem": "134217728",
            "max_swap": "268435456",
            "networks": [
              {
                "gateway": "10.88.88.2",
                "global_nic": "external",
                "index": "0",
                "ip": "10.88.88.14",
                "mac_addr": "90:b8:d0:68:e6:3d",
                "netmask": "255.255.255.0",
                "physical": "net0",
                "vlan_id": "0"
              }
            ],
            "status": "running",
            "zfs_io_priority": "1",
            "zone": "6854af30-30f9-488c-ae5f-7efd39c407e7",
            "zone_uuid": "6854af30-30f9-488c-ae5f-7efd39c407e7"
          },
          "period_end": "2011-11-09T16:00:00.002Z",
          "period_start": "2011-11-09T15:59:57.710Z"
        }
      ]
    },
    "metering": {
      "network": {
        "net0": {
          "bytes_received_delta": 313994,
          "bytes_sent_delta": 3150,
          "period_end": "2011-11-09T18:24:50.958Z",
          "period_start": "2011-11-08T21:33:29.164Z"
        }
      }
    },
    "owner_uuid": "e18652e6-0d54-4c2a-9636-c63bb9c5abb8",
    "zone_uuid": "6854af30-30f9-488c-ae5f-7efd39c407e7"
  }
}
 */
public class Report extends HashMap<String, Map> {

    public <T> T getValue(String machineUUID, String xpath, Class<T> type) {
        return (T) JXPathContext.newContext(this.get(machineUUID)).getValue(xpath, type);
    }

    /**
     * Helper method that traverses the networks to obtain the total network
     * consumption (received + sent) for a given virtual machine, in bytes
     * @param machineUUID
     * @return
     */
    public int getNetworkConsumption(String machineUUID) {
        int result = 0;

        /*
        "metering": {
          "network": {
            "net0": {
              "bytes_received_delta": 313994,
              "bytes_sent_delta": 3150,
              "period_end": "2011-11-09T18:24:50.958Z",
              "period_start": "2011-11-08T21:33:29.164Z"
            }
          }
        },
         */
        Map<String, Map> map = getValue(machineUUID, "//metering/network", Map.class);
        for (Map.Entry<String, Map> entry : map.entrySet()) {
            result += (Integer) entry.getValue().get("bytes_received_delta");
            result += (Integer) entry.getValue().get("bytes_sent_delta");
        }

        return result;
    }
}
