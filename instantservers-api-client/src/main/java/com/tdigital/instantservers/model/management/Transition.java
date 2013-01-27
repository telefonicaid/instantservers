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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transition {
    private static final Pattern PATTERN_UUID = Pattern.compile("(.+)?([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})(.+)?"); // UUID

    private String uri; // i.e. /transitions/zones/61412d23-f7e0-49a0-9d78-c272554f5760

    private boolean success;
    private int progress;
    private String message;
    private String name;

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public int getProgress() {
        return progress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    @JsonIgnore
    public String getZoneUuid() {
        assert uri != null;

        Matcher matcher = PATTERN_UUID.matcher(uri);
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            throw new IllegalStateException("Wront transition uri");
        }
    }
}
