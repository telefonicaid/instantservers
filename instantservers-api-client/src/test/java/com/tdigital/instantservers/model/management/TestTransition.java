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

import org.junit.Assert;
import org.junit.Test;

public class TestTransition {
    @Test
    public void shouldExtractZoneUuid() {
        String uri = "http://151.1.224.140/zones/22e51a6b-fe6c-49ff-8d44-ecaa9244de0d/transition";
        Transition transition = new Transition();
        transition.setUri(uri);
        Assert.assertEquals("22e51a6b-fe6c-49ff-8d44-ecaa9244de0d", transition.getZoneUuid());
    }
    @Test
    public void shouldExtractAlternativeZoneUuid() {
        String uri = "http://151.1.224.140/transitions/zones/22e51a6b-fe6c-49ff-8d44-ecaa9244de0d";
        Transition transition = new Transition();
        transition.setUri(uri);
        Assert.assertEquals("22e51a6b-fe6c-49ff-8d44-ecaa9244de0d", transition.getZoneUuid());
    }
}