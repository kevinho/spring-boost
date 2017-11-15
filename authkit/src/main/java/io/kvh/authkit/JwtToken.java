package io.kvh.authkit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 *
 * @author changbinhe
 * @date 2017/1/19
 */
public class JwtToken implements Token<Long> {

    private String secret;

    public JwtToken(String secret) {
        this.secret = secret;
    }

    @Override
    public String generate(Long userId) {
        try {
            return JWT.create()
                    .withIssuedAt(new Date())
                    .withIssuer(String.valueOf(userId))
                    .sign(Algorithm.HMAC256(secret));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Long verify(String token) {
        JWTVerifier verifier = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        DecodedJWT jwt = verifier.verify(token);
        return Long.parseLong(jwt.getIssuer());
    }
}
