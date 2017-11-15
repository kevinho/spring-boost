package io.kvh.authkit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by changbinhe on 2017/1/19.
 */
public class JwtTokenTest {

    @Test
    public void validate() {
        String secret = "aabb";
        long userId = 10000;

        JwtToken jwtToken = new JwtToken(secret);

        String token = jwtToken.generate(userId);

        System.out.println(token);

        Assert.assertEquals(userId, (long) jwtToken.verify(token));
    }

    @Test
    public void generate() {
        String secret = "e266LKq2zy6m3fa3";

        JwtToken jwtToken = new JwtToken(secret);
        long userId = 3;
        String token = jwtToken.generate(userId);

        System.out.println(token);

    }
}
