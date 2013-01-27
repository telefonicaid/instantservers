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

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Common JSON utilities. It hides the details behind Jackson Mapper.
 */
public class JSONUtils {
    private static final boolean DEV_ENV = false;
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new UnderscoreNamingStrategy());

        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        if (DEV_ENV) {
            mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); // only for debug
        }

        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
    }

    public static <T> T deserialize(final String json, final Class<T> clazz) throws IOException {
        return mapper.readValue(json.getBytes(Charset.defaultCharset()), clazz);
    }

    public static <T> T tryDeserialize(final String json, final Class<T> clazz) {
        try {
            return deserialize(json, clazz);
        } catch (IOException e) {
            /* best effort */
            return null;
        }
    }

    public static String serialize(final Object object) throws IOException {
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, object);
        return sw.toString();
    }

    public static String trySerialize(final Object object) {
        try {
            return serialize(object);
        } catch (IOException e) {
            /* best effort */
            return null;
        }
    }

    public static Map<String, Object> serializeToMap(final Object object) {
        return mapper.convertValue(object, Map.class);
    }
}
