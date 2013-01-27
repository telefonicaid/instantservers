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

import java.util.Date;

/**
 * Utility class that will encode and decode dates from/to alphanumeric strings.
 */
public final class DateEncoder {

    private static final String ALLOWED_CHARS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String SEPARATOR = "_";

    // new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").parse("2010-01-01 00:00:00+0000").getTime();
    private static final long INITIAL_DATE = 1262304000000L; // 1st of January of 2010, GMT

    /**
     * Encodes a date as a string.
     */
    public static String encode(Date date) {
        // Check the date is after the initial date
        if (date.getTime() < INITIAL_DATE) {
            throw new RuntimeException("The date " + date + " is before the initial date (in ms) " + INITIAL_DATE);
        }
        // Number of seconds from initial date
        long seconds = (date.getTime() - INITIAL_DATE) / 1000L;
        // Will contain the encoded string
        String output = new String();

        do {
            int remain = (int) (seconds % ALLOWED_CHARS.length());
            seconds = seconds / ALLOWED_CHARS.length();
            output = ALLOWED_CHARS.charAt(remain) + output;
        } while (seconds > 0);

        return output;
    }

    /**
     * Append encoded date suffix with maximal length.
     */
    public static String appendEncodedDate(String prefix, Date dateToEncode, int maxLength) {
        String suffix = getSeparator() + encode(dateToEncode);
        if (prefix.length() + suffix.length() > maxLength) {
            return prefix.substring(0, maxLength - suffix.length()) + suffix;
        } else {
            return prefix + suffix;
        }
    }

    /**
     * Return original login (could be shorter than original).
     */
    public static String extractLogin(String appendedLogin) {
        return appendedLogin.substring(0, appendedLogin.lastIndexOf(getSeparator()));
    }

    /**
     * Return original date.
     */
    public static Date extractDate(String appendedLogin) {
        String encodedDate = appendedLogin.substring(
                appendedLogin.lastIndexOf(getSeparator()) + 1,
                appendedLogin.length());
        return decode(encodedDate);
    }

    /**
     * Encodes a string as a date.
     */
    public static Date decode(final String encodedString) {
        long seconds = 0L;

        for (byte currentChar : encodedString.getBytes()) {
            int charOrder = getCharOrder(currentChar);
            seconds = (seconds * ALLOWED_CHARS.length()) + charOrder;
        }

        return new Date((seconds * 1000L) + INITIAL_DATE);
    }

    private static int getCharOrder(byte character) {
        for (int i = 0; i < ALLOWED_CHARS.length(); i++) {
            if (character == ALLOWED_CHARS.charAt(i)) {
                return i;
            }
        }
        throw new RuntimeException("The selected character was not valid: " + character);
    }

    /**
     * Get separator prefix/suffix.
     */
    public static String getSeparator() {
        return SEPARATOR;
    }

    private DateEncoder() {
        throw new RuntimeException("Utility class should not be instantiated");
    }
}
