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
 * This class exists to parse the Zone JSON like:
 * 
 *    "credentials":[
 *       {
 *          "system":"root",
 *          "username":"root",
 *          "password":"aw;+sFrT:+"
 *       },
 *       {
 *          "system":"pepito",
 *          "username":"pepito",
 *          "password":"R^3f}.:xkw"
 *       },
 *       {
 *          "system":"admin",
 *          "username":"admin",
 *          "password":"KPRDmreR97"
 *       }
 *    ]
 */
public class Credential {

    private String system;
    private String username;
    private String password;
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
