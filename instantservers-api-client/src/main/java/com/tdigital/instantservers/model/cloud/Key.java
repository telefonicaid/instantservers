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

//{
//  "name": "rsa",
//  "key": "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEA0A5Pf5Cq...",
//  "created": "2011-04-13T22:14:46+00:00",
//}
public class Key {
	private String name;
	private String key;
	private Date created;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
