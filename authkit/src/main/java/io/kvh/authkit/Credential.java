package io.kvh.authkit;

/**
 * Created by changbinhe on 2017/1/19.
 */
public interface Credential {
    String generate(String raw);

    boolean verify(String raw, String dst);
}
