package io.kvh.authkit;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by changbinhe on 2017/1/19.
 */
public class SaltyCredential implements Credential {

    private String salt;

    public SaltyCredential(String salt) {
        this.salt = salt;
    }

    @Override
    public String generate(String raw) {
        return md5(raw + this.salt);
    }

    public String generate(String raw, String salt) {
        return md5(raw + salt);
    }

    @Override
    public boolean verify(String rawInput, String saved) {
        String dst = generate(rawInput);
        return dst.equals(saved);
    }

    public static String md5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(String.format("%02x", array[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getSalt() {
        return salt;
    }
}
