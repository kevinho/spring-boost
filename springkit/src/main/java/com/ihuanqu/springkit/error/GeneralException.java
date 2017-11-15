package com.ihuanqu.springkit.error;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author changbinhe
 * @date 2016/12/29
 */

@ToString
@Getter
public class GeneralException extends Exception {

    private int code;

    public GeneralException(String msg, int code) {
        super(msg);
        this.code = code;
    }
}
