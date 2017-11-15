package com.ihuanqu.springkit.sms;

/**
 * @author changbinhe
 * @date 2017/1/20
 */
public interface SmsDeliver {
    /**
     * 发送
     *
     * @param mobile
     * @param message
     * @return
     */
    boolean send(String mobile, String message);
}
