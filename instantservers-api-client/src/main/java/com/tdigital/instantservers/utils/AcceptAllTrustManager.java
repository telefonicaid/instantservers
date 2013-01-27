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

import javax.net.ssl.X509TrustManager;

public class AcceptAllTrustManager implements X509TrustManager {

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
            throws java.security.cert.CertificateException {
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
            throws java.security.cert.CertificateException {
    }
}

