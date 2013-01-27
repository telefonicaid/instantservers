/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Common API utilities
 */
public class InstantServersApiUtils {
    /**
     * Converts a date to the format required by the API, i.e. 2011-10-31T14:36:46.881Z
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s'Z'");
        return sdf.format(date);
    }
}
