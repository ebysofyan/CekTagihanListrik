package com.lc.belajar.retrofit.services.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.CertificateException;

public class SocketFactoryUtil {

    public static final String jks = "com/lifecode/daloket/retrofit/resources/daloket.rsud.mataramkota.go.id.p12";
    public static final String password = "m4r4t4mdu5r";

    public static KeyStore readKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException {
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore ks = KeyStore.getInstance(keyStoreType);
        // get user password and file input stream
        char[] passwd = SocketFactoryUtil.password.toCharArray();

        java.io.InputStream fis = null;
        try {
            fis = SocketFactoryUtil.class.getClassLoader().getResourceAsStream(jks);
            ks.load(fis, passwd);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.security.cert.CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        return ks;
    }

    public static SSLSocketFactory getSocketFactory() {
        try {
            KeyStore keyStore = SocketFactoryUtil.readKeyStore();
            SSLContext sslContext = SSLContext.getInstance("SSL");

            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, SocketFactoryUtil.password.toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | KeyManagementException
                | UnrecoverableKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
