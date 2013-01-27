package com.tdigital.instantservers.utils;/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */

import com.tdigital.instantservers.model.customer.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

public class TestJSONUtils {
    private TimeZone tz = TimeZone.getTimeZone("UTC");

    @Test
    public void serializeEmptyObject() throws IOException {
        Customer c = new Customer();
        String json = JSONUtils.serialize(c);
        Assert.assertEquals("{}", json);
    }

    @Test
    public void serializeNullProperties() throws IOException {
        Customer c = new Customer();
        c.setEmailAddress(null);
        String json = JSONUtils.serialize(c);
        Assert.assertEquals("{}", json);
    }

    @Test
    public void serializeObjectWithDates() throws IOException {
        Customer c = new Customer();

        Calendar cal = Calendar.getInstance(tz);
        cal.set(2011, Calendar.JANUARY, 10, 13, 10, 0);
        cal.set(Calendar.MILLISECOND, 0);

        c.setUpdatedAt(cal.getTime());

        String json = JSONUtils.serialize(c);
        Assert.assertEquals("{\"updated_at\":\"2011-01-10T13:10:00.000+0000\"}", json);
    }

    @Test
    public void serializeObjectWithIntegerValues() throws IOException {
        Customer c = new Customer();
        c.setRole(2);

        String json = JSONUtils.serialize(c);
        Assert.assertEquals("{\"role\":2}", json);
    }

    @Test
    public void serializeCamelPropertyNames() throws IOException {
        Customer c = new Customer();
        c.setEmailAddress("pepe@pepe.com");

        String json = JSONUtils.serialize(c);
        Assert.assertEquals("{\"email_address\":\"pepe@pepe.com\"}", json);
    }

    @Test
    public void deserializeCamelPropertyNames() throws IOException {
        String json = "{\"company_name\":\"new company name\"}";
        Customer c = JSONUtils.deserialize(json, Customer.class);

        Assert.assertEquals("new company name", c.getCompanyName());
    }

    @Test
    public void testDateDeserializeWithDocumentationFormat() throws IOException {
        String json = "{\"updated_at\":\"2011-06-03T00:02:31+00:00\"}";
        Customer c = JSONUtils.deserialize(json, Customer.class);

        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(c.getUpdatedAt());

        Assert.assertEquals(2011, cal.get(Calendar.YEAR));
        Assert.assertEquals(6 - 1, cal.get(Calendar.MONTH)); // months start with 0
        Assert.assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(2, cal.get(Calendar.MINUTE));
        Assert.assertEquals(31, cal.get(Calendar.SECOND));
    }

    @Test
    public void testDateDeserializeWithJavaISO8601Format() throws IOException {
        String json = "{\"updated_at\":\"2011-01-10T12:10:00.762+0000\"}";
        Customer c = JSONUtils.deserialize(json, Customer.class);

        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(c.getUpdatedAt());

        Assert.assertEquals(2011, cal.get(Calendar.YEAR));
        Assert.assertEquals(1 - 1, cal.get(Calendar.MONTH)); // months start with 0
        Assert.assertEquals(10, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(10, cal.get(Calendar.MINUTE));
        Assert.assertEquals(0, cal.get(Calendar.SECOND));
    }

    @Test
    public void testTryDeserializeShouldNotFail() {
        String wrongJson = "this_is_not_a_json";
        Customer customer = JSONUtils.tryDeserialize(wrongJson, Customer.class);
        Assert.assertNull(customer);
    }
}
