package com.tdigital.instantservers.utils;/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */

import junit.framework.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test class for encoding/decoding dates.
 */
public class DateEncoderTest {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SDF_MILLIS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    /**
     * Test encode/decode of dates.
     */
    @Test
    public void testEncodeDecode() throws Exception {
        String[] dates = {
            "2010-01-01 01:01:25.123",
            "2010-03-12 16:59:59.877",
            "2011-12-31 23:44:36.099",
            "2012-11-28 10:33:25.001",
            "2020-11-28 10:33:25.456",
            "2030-11-28 10:33:25.456",
            "2039-01-12 10:20:30.912",
            "2039-01-12 10:20:32.000",
            "2036-11-28 10:33:25.999",
            "2045-11-28 10:33:25.789",
            "2088-11-28 10:33:25.173",
            "2099-11-28 10:33:25.674",
            "2111-11-28 10:33:25.182",
            "2136-11-28 10:33:25.882",
            "2236-11-28 10:33:25.882",
            "2336-11-28 10:33:25.882",
            "2436-11-28 10:33:25.882"
        };

        for (String sDate : dates) {
            Date date = SDF_MILLIS.parse(sDate);

            String encodedDate = DateEncoder.encode(date);

            Assert.assertNotSame(sDate, encodedDate);

            String decodedDate = SDF.format(DateEncoder.decode(encodedDate));

            System.out.println("Original = " + sDate + ", encoded = " + encodedDate + ", decoded = " + decodedDate);

            Assert.assertEquals(SDF.format(date), decodedDate);
        }
    }

    /**
     * Test append encoded dates.
     */
    @Test
    public void testAppendEncodedDate() throws ParseException {
        String[] logins = {
            "ilopez", // 6
            "very_long_login_to_try_this", // 27
            "very_long_login_to_try_this2", // 28
            "very_long_login_to_try_this_3", // 29
            "very_long_login_to_try_this_out", // 31
            "very_long_login_to_try_this_out2", // 32
            "abcdefchijklmnopqrstuvwxyz_manic", // 32
            "abcdefchijklmnopqrstuvwxy_zmanic", // 32
            "another_long_login" // 18
        };
        String[] expectedOutputs = {
            "ilopez_zzzzz", // 12
            "very_long_login_to_try_thi_zzzzz", // 27
            "very_long_login_to_try_thi_zzzzz", // 28
            "very_long_login_to_try_thi_zzzzz", // 29
            "very_long_login_to_try_thi_zzzzz", // 31
            "very_long_login_to_try_thi_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxyz_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxy__zzzzz", // 32
            "another_long_login_zzzzz" // 24
        };
        Date zzzzzDate = SDF.parse("2039-01-12 10:20:31");

        for (int i = 0; i < logins.length; i++) {
            Assert.assertEquals(expectedOutputs[i], DateEncoder.appendEncodedDate(logins[i], zzzzzDate, 32));
        }
    }

    /**
     * Test extract encoded dates.
     */
    @Test
    public void testExtractDate() throws ParseException {
        String[] encodedLogins = {
            "ilopez_zzzzz", // 12
            "very_long_login_to_try_thi_zzzzz", // 27
            "very_long_login_to_try_thi_zzzzz", // 28
            "very_long_login_to_try_thi_zzzzz", // 29
            "very_long_login_to_try_thi_zzzzz", // 31
            "very_long_login_to_try_thi_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxyz_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxy__zzzzz", // 32
            "another_long_login_zzzzz" // 24
        };
        Date zzzzzDate = SDF.parse("2039-01-12 10:20:31");

        for (int i = 0; i < encodedLogins.length; i++) {
            Assert.assertEquals(zzzzzDate.getTime(), DateEncoder.extractDate(encodedLogins[i]).getTime());
        }
    }

    /**
     * Test extract encoded dates.
     */
    @Test
    public void testExtractLogin() throws ParseException {
        String[] encodedLogins = {
            "ilopez_zzzzz", // 12
            "very_long_login_to_try_thi_zzzzz", // 27
            "very_long_login_to_try_thi_zzzzz", // 28
            "very_long_login_to_try_thi_zzzzz", // 29
            "very_long_login_to_try_thi_zzzzz", // 31
            "very_long_login_to_try_thi_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxyz_zzzzz", // 32
            "abcdefchijklmnopqrstuvwxy__zzzzz", // 32
            "another_long_login_zzzzz" // 24
        };
        String[] expectedLogins = {
            "ilopez", // 6
            "very_long_login_to_try_thi", // 26
            "very_long_login_to_try_thi", // 26
            "very_long_login_to_try_thi", // 26
            "very_long_login_to_try_thi", // 26
            "very_long_login_to_try_thi", // 26
            "abcdefchijklmnopqrstuvwxyz", // 26
            "abcdefchijklmnopqrstuvwxy_", // 26
            "another_long_login" // 18
        };

        for (int i = 0; i < encodedLogins.length; i++) {
            Assert.assertEquals(expectedLogins[i], DateEncoder.extractLogin(encodedLogins[i]));
        }
    }

    /**
     * Test encode/decode of dates.
     */
    @Test
    public void testLimitDates() throws Exception {
        String[] endodedDates = {
            "0", // = 0
            "1", // = 1
            "z", // = 61
            "0z", // = 61
            "10", // = 62
            "asjkd",
            "zz",
            "100",
            "zzz",
            "1000",
            "zzzz",
            "10000",
            "zzzzz",
            "100000",
            "zzzzzz",
            "1000000"
        };

        for (String encodedDate : endodedDates) {
            String decodedDate = SDF.format(DateEncoder.decode(encodedDate));

            System.out.println("Encoded = " + encodedDate + ", decoded = " + decodedDate);
        }
    }
}
