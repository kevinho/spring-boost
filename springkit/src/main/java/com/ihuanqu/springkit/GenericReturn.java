package com.ihuanqu.springkit;

import lombok.Data;

@Data
public class GenericReturn<T> {

    private String msg;
    private Integer ret = 0;

    private T data;

    public void setResult(boolean result) {
        ret = result ? 0 : 1;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}