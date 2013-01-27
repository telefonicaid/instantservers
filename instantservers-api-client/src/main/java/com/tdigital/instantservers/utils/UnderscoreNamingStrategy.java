/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.utils;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

public class UnderscoreNamingStrategy extends PropertyNamingStrategy {
    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field,
            String defaultName) {
        JsonProperty p = field.getAnnotation(JsonProperty.class);

        if (p != null) {
            return convert(p.value());
        } else {
            return convert(defaultName);
        }
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config,
            AnnotatedMethod method, String defaultName) {
        return convert(defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config,
            AnnotatedMethod method, String defaultName) {
        return convert(defaultName);
    }

    private String convert(String input) {
        // easy: replace capital letters with underscore, lower-cases equivalent
        StringBuilder result = new StringBuilder();
        for (int i = 0, len = input.length(); i < len; ++i) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append('_');
                c = Character.toLowerCase(c);
            }
            result.append(c);
        }
        return result.toString();
    }
}