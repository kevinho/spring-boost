package io.kvh.springkit;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by changbinhe on 09/12/2016.
 */

public class AccessTokenTest {

    Logger logger = LoggerFactory.getLogger(AccessTokenTest.class);

    @Test
    public void generate() {
        String token = AccessToken.generate(403);

        logger.debug("{}", token);

        Assert.assertNotNull(token);
    }

    @Test
    public void verify() throws AccessToken.AccessTokenException {
        String token = "ENSPQNVjFfMTAwX2IyOTFlZTNmN2FjN2JlMWQ0MjAxNjg5MDkwNGJmOTViXzE0ODM4NjQwMzQ=";

        long userId = 100;
        long expiresIn = 1483864034;

        AccessToken.VerifyResult result = AccessToken.verify(token);

        logger.debug("{}", result);

        Assert.assertEquals(userId, result.getUserId());
        Assert.assertEquals(expiresIn, result.getExpiresIn());
    }

    @Test(expected = AccessToken.AccessTokenException.class)
    public void errorVerify() throws AccessToken.AccessTokenException {
        String token = "add->ENSPQNVjFfMTAwX2IyOTFlZTNmN2FjN2JlMWQ0MjAxNjg5MDkwNGJmOTViXzE0ODM4NjQwMzQ=";

        AccessToken.VerifyResult result = AccessToken.verify(token);
    }

    @Test(expected = AccessToken.AccessTokenException.class)
    public void errorVerify2() throws AccessToken.AccessTokenException {
        String token = "dGhpcyBpcyBhIGV4YW1wbGU=";

        AccessToken.VerifyResult result = AccessToken.verify(token);
    }

}
