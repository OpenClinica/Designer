package org.akaza.openclinica.designer.core;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NaiveTrustManager implements X509TrustManager {

    private static SSLSocketFactory sslSocketFactory;

    /**
     * Doesn't throw an exception, so this is how it approves a certificate.
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], String)
     **/
    @Override
    public void checkClientTrusted(X509Certificate[] cert, String authType) throws CertificateException {
    }

    /**
     * Doesn't throw an exception, so this is how it approves a certificate.
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], String)
     **/
    @Override
    public void checkServerTrusted(X509Certificate[] cert, String authType) throws CertificateException {
    }

    /**
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     **/
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null; // I've seen someone return new X509Certificate[ 0 ];
    }

    /**
     * Returns a SSL Factory instance that accepts all server certificates.
     * 
     * <pre>
     * SSLSocket sock = (SSLSocket) getSocketFactory.createSocket(host, 443);
     * </pre>
     * @return An SSL-specific socket factory.
     **/
    public static final SSLSocketFactory getSocketFactory() {
        if (sslSocketFactory == null) {
            try {
                TrustManager[] tm = new TrustManager[] { new NaiveTrustManager() };
                SSLContext context = SSLContext.getInstance("SSL");
                context.init(new KeyManager[0], tm, new SecureRandom());

                sslSocketFactory = context.getSocketFactory();

            } catch (KeyManagementException e) {
                // log.error("No SSL algorithm support: " + e.getMessage(), e);
            } catch (NoSuchAlgorithmException e) {
                // log.error("Exception when setting up the Naive key management.", e);
            }
        }
        return sslSocketFactory;
    }

}
