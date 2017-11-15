package io.kvh.authkit;

import org.junit.Test;

/**
 * Created by changbinhe on 2017/1/19.
 */
public class SaltyCredentialTest {

    @Test
    public void test() {
        String secret = "aabb";
        long userId = 10000;

        System.out.println(SaltyCredential.md5("151151"));


        String raw = SaltyCredential.md5(String.valueOf(userId));

        SaltyCredential saltyCredential = new SaltyCredential(secret);

        String token = saltyCredential.generate(raw);

        System.out.println(token);
    }
}
