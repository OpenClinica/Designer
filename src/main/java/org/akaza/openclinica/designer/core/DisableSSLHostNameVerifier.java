package org.akaza.openclinica.designer.core;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class DisableSSLHostNameVerifier {

    /**
     * Disable SSL Host Name verification process. Obviously a security risk, But our customers often install OC with a self
     * signed cert and wrong CN names.
     */
    public static void disableSSLHostNameVerifier() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
    }

}
