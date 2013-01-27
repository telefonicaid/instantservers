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


//{
//    "id":4,
//    "uuid":"fa07b5b6-118e-11e1-913c-3f246c23837c",
//    "creator_uuid":"352971aa-31ba-496c-9ade-a379feaecd52",
//    "restricted_to_uuid":null,
//    "name":"fedora14",
//    "version":"0.1.0",
//    "type":"zvol",
//    "description":" Fedora 14 (minimal) 0.1.0 VM image",
//    "os":"linux",
//    "default":false,
//    "cloud_name":"sdc",
//    "creator_name":"sdc",
//    "urn":"sdc:sdc:fedora14:0.1.0",
//    "generate_passwords":true,
//    "inherited_directories":null,
//    "files":[
//       {
//          "path":"fc14-0.1.0.zvol.gz",
//          "sha1":"910b4310d4e3017c4423c76c75a96d58d03f7b70",
//          "size":528485454,
//          "url":"https://datasets.joyent.com/datasets/fa07b5b6-118e-11e1-913c-3f246c23837c/fc14-0.1.0.zvol.gz"
//       }
//    ],
//    "cpu_type":"host",
//    "nic_driver":"virtio",
//    "disk_driver":"virtio",
//    "image_size":10240,
//    "users":{
//
//    },
//    "requirements":{
//       "networks":[
//          {
//             "name":"net0",
//             "description":"public"
//          }
//       ],
//       "ssh_key":true
//    },
//    "platform_type":"smartos",
//    "published_at":"2011-11-19T00:04:52+00:00",
//    "created_at":"2012-02-09T23:53:16+00:00",
//    "updated_at":"2012-02-10T00:05:52+00:00",
//    "imported_at":"2012-02-10T00:05:52+00:00",
//    "disabled_at":null,
//    "owner_uuid":null,
//    "vendor_uuid":"352971aa-31ba-496c-9ade-a379feaecd52",
//    "uri":"/datasets/4"
//}
public class Dataset {
    private String name;
    private String version;
    private String os;
    private String id;
    private String description;
    private String urn;
    private String type; // zvol (virtualmachine) or zone-dataset (smartmachine)
    private DatasetRequirements requirements;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public DatasetRequirements getRequirements() {
        return requirements;
    }
    public void setRequirements(DatasetRequirements requirements) {
        this.requirements = requirements;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @JsonIgnore
    public boolean isSmartMachine() {
        return "zone-dataset".equals(type);
    }
}
