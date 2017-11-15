package io.kvh.authkit;

/**
 * @author changbinhe
 * @date 2017/1/19
 */
public interface Token<T> {
    /**
     * generate token from id
     *
     * @param id
     * @return token
     */
    public String generate(T id);

    /**
     * verify token
     *
     * @param token
     * @return info contains in token
     */
    public T verify(String token);
}
