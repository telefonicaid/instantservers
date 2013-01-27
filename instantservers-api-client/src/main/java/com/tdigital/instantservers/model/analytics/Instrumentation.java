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

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

/*
[
  {
    "module": "syscall",
    "stat": "syscalls",
    "predicate": {},
    "decomposition": [],
    "value-dimension": 1,
    "value-arity": "scalar",
    "enabled": true,
    "retention-time": 600,
    "idle-max": 3600,
    "transformations": {},
    "nsources": 1,
    "granularity": 1,
    "persist-data": false,
    "crtime": 1309457451143,
    "value-scope": "interval",
    "id": "42",
    "uris": [{
      "uri": "/admin/analytics/instrumentations/42/value/raw",
      "name": "value_raw"
    }]
  }
}
 */
public class Instrumentation {
    private String module;

    private String stat;

    /**
     * Instrumentation Predicate. Currently it only supports one predicate
     * i.e. { eq: [ fieldname, value ] })
     */
    private Map<PredicateOperator, String[]> predicate;

    private DecompositionField[] decomposition;

    @JsonProperty("value-dimension")
    private int valueDimension;

    /**
     * scalar, discrete-decomposition, numeric-decomposition
     */
    @JsonProperty("value-arity")
    private String valueArity;

    @JsonProperty("retention-time")
    private int retentionTime;

    private int granularity;

    /**
     * Number of seconds after which if the instrumentation or its data has not
     * been accessed via the API the service may delete the instrumentation and its data
     */
    @JsonProperty("idle-max")
    private int idleMax;

    // private Transformation[] transformations;

    private int nsources;

    @JsonProperty("persist-data")
    private boolean persistData;

    @JsonProperty("value-scope")
    private String valueScope;

    private String id;

    private long crtime;

    private Uri[] uris;

    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public String getStat() {
        return stat;
    }
    public void setStat(String stat) {
        this.stat = stat;
    }
    public DecompositionField[] getDecomposition() {
        return decomposition;
    }
    public void setDecomposition(DecompositionField[] decomposition) {
        this.decomposition = decomposition;
    }
    /**
     * Helper method to simplify the most common case (only one decomposition)
     * @param decompositionField
     */
    public void setDecompositionField(DecompositionField decompositionField) {
        this.decomposition = new DecompositionField[] { decompositionField };
    }

    public int getValueDimension() {
        return valueDimension;
    }
    public void setValueDimension(int valueDimension) {
        this.valueDimension = valueDimension;
    }
    public String getValueArity() {
        return valueArity;
    }
    public void setValueArity(String valueArity) {
        this.valueArity = valueArity;
    }
    public int getRetentionTime() {
        return retentionTime;
    }
    public void setRetentionTime(int retentionTime) {
        this.retentionTime = retentionTime;
    }
    public int getGranularity() {
        return granularity;
    }
    public void setGranularity(int granularity) {
        this.granularity = granularity;
    }
    public int getIdleMax() {
        return idleMax;
    }
    public void setIdleMax(int idleMax) {
        this.idleMax = idleMax;
    }
    public int getNsources() {
        return nsources;
    }
    public void setNsources(int nsources) {
        this.nsources = nsources;
    }
    public boolean isPersistData() {
        return persistData;
    }
    public void setPersistData(boolean persistData) {
        this.persistData = persistData;
    }
    public String getValueScope() {
        return valueScope;
    }
    public void setValueScope(String valueScope) {
        this.valueScope = valueScope;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Uri[] getUris() {
        return uris;
    }
    public void setUris(Uri[] uris) {
        this.uris = uris;
    }
    public long getCrtime() {
        return crtime;
    }
    public void setCrtime(long crtime) {
        this.crtime = crtime;
    }
    public Map<PredicateOperator, String[]> getPredicate() {
        return predicate;
    }
    public void setPredicate(Map<PredicateOperator, String[]> predicate) {
        this.predicate = predicate;
    }
}
