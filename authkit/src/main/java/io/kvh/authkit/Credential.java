package io.kvh.authkit;

/**
 * @author changbinhe
 * @date 2017/1/19
 */
public interface Credential {
    /**
     * generate encrypted password from raw
     *
     * @param raw
     * @return
     */
    String generate(String raw);

    /**
     * verify the password
     *
     * @param src
     * @param dst
     * @return
     */
    boolean verify(String input, String target);
}
