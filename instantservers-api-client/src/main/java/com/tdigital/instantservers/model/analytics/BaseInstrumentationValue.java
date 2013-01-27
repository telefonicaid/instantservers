/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.model.analytics;

public abstract class BaseInstrumentationValue {

    private long startTime;
    private int duration;
    private int nsources;
    private int minreporting;
    private long requestedStartTime;
    private long requestedDuration;
    private long requestedEndTime;

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getNsources() {
        return nsources;
    }
    public void setNsources(int nsources) {
        this.nsources = nsources;
    }
    public int getMinreporting() {
        return minreporting;
    }
    public void setMinreporting(int minreporting) {
        this.minreporting = minreporting;
    }
    public long getRequestedStartTime() {
        return requestedStartTime;
    }
    public void setRequestedStartTime(long requestedStartTime) {
        this.requestedStartTime = requestedStartTime;
    }
    public long getRequestedDuration() {
        return requestedDuration;
    }
    public void setRequestedDuration(long requestedDuration) {
        this.requestedDuration = requestedDuration;
    }
    public long getRequestedEndTime() {
        return requestedEndTime;
    }
    public void setRequestedEndTime(long requestedEndTime) {
        this.requestedEndTime = requestedEndTime;
    }
}
