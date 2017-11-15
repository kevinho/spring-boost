package io.kvh.authkit;

/**
 * @author changbinhe
 * @date 2017/1/18
 */
public class AuthKit {

    private JwtToken jwtToken;
    private SaltyCredential saltyCredential;

    public static AuthKit with(String secret, String salt) {
        return new AuthKit(secret, salt);
    }

    private AuthKit(String secret, String salt) {
        jwtToken = new JwtToken(secret);
        saltyCredential = new SaltyCredential(salt);
    }

    public JwtToken getJwtToken() {
        return jwtToken;
    }

    public SaltyCredential getSaltyCredential() {
        return saltyCredential;
    }
}
