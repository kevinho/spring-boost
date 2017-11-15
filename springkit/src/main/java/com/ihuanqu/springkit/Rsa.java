package com.ihuanqu.springkit;


import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by changbinhe on 2016/12/15.
 */
public class Rsa {

    public static PublicKey readPublicKeyFromFile(String fileName) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(fileName).toPath());
        keyBytes = Base64.getMimeDecoder().decode(keyBytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static PrivateKey readPrivateKeyFromFile(String fileName) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(fileName).toPath());
        keyBytes = Base64.getMimeDecoder().decode(keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式

    //todo > 127 byte would be problem
    public static byte[] decryptData(byte[] data, PrivateKey privateKey) throws IOException {
        byte[] descryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            descryptedData = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return descryptedData;
    }
    public static byte[] encryptData(String data, PublicKey pubKey) throws IOException {
        byte[] dataToEncrypt = data.getBytes();
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encryptedData = cipher.doFinal(dataToEncrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedData;
    }


}
