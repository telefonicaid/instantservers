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

/*
{
  "modules": {
    "cpu": { "label": "CPU" },
    "memory": { "label": "Memory" },
    ...
  },
  "fields": {
    "hostname": {
      "label": "server hostname",
      "type": "string"
    },
    "runtime": {
      "label": "time on CPU",
      "type": "time"
    },
    "zonename": {
      "label": "zone name",
      "type": "string"
    }
  },
  "types": {
    "string": {
      "arity": "discrete",
      "unit": ""
    },
    "size": {
      "arity": "numeric",
      "unit": "bytes",
      "abbr": "B",
      "base": 2,
    },
    "time": {
      "arity": "numeric",
      "unit": "seconds",
      "abbr": "s",
      "base": 10,
      "power": -9,
    }
  },
  "metrics": [ {
    "module": "cpu",
    "stat": "thread_executions",
    "label": "thread executions",
    "interval": "interval",
    "fields": [ "hostname", "zonename", "runtime" ],
    "unit": "operations"
  }, {
    "module": "memory",
    "stat": "rss",
    "label": "resident set size",
    "interval": "point",
    "fields": [ "hostname", "zonename" ],
    "type": "size"
  } ],
  "transformations": {
    "geolocate": {
      "label": "geolocate IP addresses",
      "fields": [ "raddr" ]
    },
    "reversedns": {
      "label": "reverse dns IP addresses lookup",
      "fields": [ "raddr" ]
    }
  }
}
 */
public class AnalyticsSchema {
    private Map<String, Module> modules;
    private Map<String, Field> fields;
    private Map<String, Map<String, String>> types;
    private Metric[] metrics;
    private Map<String, Transformation> transformations;

    public Map<String, Module> getModules() {
        return modules;
    }
    public void setModules(Map<String, Module> modules) {
        this.modules = modules;
    }
    public Map<String, Field> getFields() {
        return fields;
    }
    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }
    public Map<String, Map<String, String>> getTypes() {
        return types;
    }
    public void setTypes(Map<String, Map<String, String>> types) {
        this.types = types;
    }
    public Metric[] getMetrics() {
        return metrics;
    }
    public void setMetrics(Metric[] metrics) {
        this.metrics = metrics;
    }
    public Map<String, Transformation> getTransformations() {
        return transformations;
    }
    public void setTransformations(Map<String, Transformation> transformations) {
        this.transformations = transformations;
    }
}
