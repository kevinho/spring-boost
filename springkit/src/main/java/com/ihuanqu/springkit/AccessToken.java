package com.ihuanqu.springkit;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

/**
 * Created by changbinhe on 09/12/2016.
 */
public class AccessToken {

    private static Logger logger = LoggerFactory.getLogger(AccessToken.class);

    private static final String VERSION = "V1";
    private static final int RANDOM_LENGTH = 6;
    private static String SECRET = "hU37C78B8t4hNT3H";

    public static void setSecret(String secret) {
        AccessToken.SECRET = secret;
    }

    @ToString
    public static class VerifyResult {
        long userId;
        long expiresIn;

        private void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

        private void setUserId(long userId) {
            this.userId = userId;
        }

        public long getUserId() {
            return userId;
        }

        public long getExpiresIn() {
            return expiresIn;
        }
    }

    /**
     * generate access token
     *
     * @param userId
     * @param expiresIn
     * @return result
     */
    public static String generate(long userId, long expiresIn) {
        String authCode = getAuthCode(userId, expiresIn);

        //4 parts
        String raw = String.format("%s_%d_%s_%d", VERSION, userId, authCode, expiresIn);

        String base64Encoded = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));

        String randomString = getRandomString(RANDOM_LENGTH);

        return String.format("%s%s", randomString, base64Encoded);
    }

    /**
     * generate access token using default expires time 30 days
     *
     * @param userId
     * @return result
     */
    public static String generate(long userId) {
        long expiresIn = System.currentTimeMillis() / 1000L + 30 * 86400;
        return generate(userId, expiresIn);
    }

    /**
     * verify access token
     *
     * @param accessToken
     * @return parsed token info
     * @throws AccessTokenException
     */
    public static VerifyResult verify(String accessToken) throws AccessTokenException {
        try {
            if (StringUtils.isEmpty(accessToken)
                    || accessToken.length() <= 6) {

                throw new AccessTokenException("length error");
            }

            String token = accessToken.substring(RANDOM_LENGTH);

            token = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);

            String[] strings = token.split("_");

            if (strings.length != 4) {
                throw new AccessTokenException("parse error");
            }

            String version = strings[0];
            long userId = Long.parseLong(strings[1]);
            String authCodePassed = strings[2];
            long expiresIn = Long.parseLong(strings[3]);

            String authCodeGen = getAuthCode(userId, expiresIn);

            if (!authCodeGen.equals(authCodePassed)) {
                throw new AccessTokenException("auth error");
            }

            if (!VERSION.equals(version)) {
                throw new AccessTokenException("version error");
            }

            VerifyResult result = new VerifyResult();

            result.setUserId(userId);
            result.setExpiresIn(expiresIn);

            return result;
        } catch (Exception ex) {
            throw new AccessTokenException(ex.getMessage());
        }
    }

    public static String getRandomString(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static String getAuthCode(long userId, long expiresIn) {
        String raw = String.format("%s%d%d", SECRET, userId, expiresIn);
        return StringUtils.md5(raw);
    }

    /**
     * Created by changbinhe on 09/12/2016.
     */
    public static class AccessTokenException extends Exception {

        public AccessTokenException(String msg) {
            super(msg);
        }
    }
}
