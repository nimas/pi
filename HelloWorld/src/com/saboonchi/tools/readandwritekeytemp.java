package com.saboonchi.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;


import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class readandwritekeytemp {
    public static PrivateKey read2048PrivateKeyfile(String privateKeyFilePathName) {
       /* Generate a DSA signature */
        PrivateKey privateKey = null;


        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);

            // reade private key from file
            FileInputStream keyFile = new FileInputStream(privateKeyFilePathName);
            byte[] encKey = new byte[keyFile.available()];
            keyFile.read(encKey);
            keyFile.close();

            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(privKeySpec);

            // create Signature
            Signature dsa = Signature.getInstance("SHA1withRSA");
            dsa.initSign(privateKey);

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
        return privateKey;
    }



    public static void signandwritesingfileKeyfile(PrivateKey privateKey,String fileToSignPathName,String singFilePathName) {
       /* Generate a DSA signature */

        try {

            // create Signature
            Signature dsa = Signature.getInstance("SHA1withRSA");
            dsa.initSign(privateKey);

            // read file
            FileInputStream fis = new FileInputStream(fileToSignPathName);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufin.read(buffer)) >= 0) {
                dsa.update(buffer, 0, len);
            };
            bufin.close();


            byte[] realSig = dsa.sign();


            /* save the signature in a file */
            FileOutputStream sigfos = new FileOutputStream(singFilePathName);
            sigfos.write(realSig);
            sigfos.close();

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}
/*
create new keystore

keytool -keystore clientkeystore -genkey -alias client

-------------------
see list of content of keystore

keytool -list -keystore [keysoter path and name]
keytool -list -keystore [keysoter path and name] -alias [name of key]
---------------

export certifiate from keystore

keytool -export -keystore examplestore -alias signFiles -file Example.cer
-----------------
print inside the certificate

keytool -printcert -file [certificate file path and name like client.cer]


*/
