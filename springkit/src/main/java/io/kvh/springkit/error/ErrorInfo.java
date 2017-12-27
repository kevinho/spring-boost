package io.kvh.springkit.error;

import lombok.Data;

/**
 * @author kvh
 * @date 2017/10/25
 */
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 999;
    private Integer code;
    private String message;
    private String url;
    private T data;
}
