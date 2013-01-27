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

/**
//    "requirements":{
//       "networks":[
//          {
//             "name":"net0",
//             "description":"public"
//          }
//       ],
//       "ssh_key":true
//    }
 */
public class DatasetRequirements {
    private Network[] networks;
    private Boolean sshKey;

    public Network[] getNetworks() {
        return networks;
    }
    public void setNetworks(Network[] networks) {
        this.networks = networks;
    }
    public Boolean getSshKey() {
        return sshKey;
    }
    public void setSshKey(Boolean sshKey) {
        this.sshKey = sshKey;
    }
}
