package io.kvh.authkit;

/**
 * Created by changbinhe on 2017/1/19.
 */
public interface Token<T> {
    public String generate(T userId);
    public T verify(String token);
}
