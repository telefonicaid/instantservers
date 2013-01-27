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

//{
//  "name": "regular_128",
//  "memory": 128,
//  "swap": 256,
//  "disk": 5120,
//  "default": true
//}
public class Package {
    private String name;
    private int memory;
    private int swap;
    private int disk;
    private boolean defaultPackage;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
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
    public boolean isDefault() {
        return defaultPackage;
    }
    public void setDefault(boolean defaultPackage) {
        this.defaultPackage = defaultPackage;
    }
}
