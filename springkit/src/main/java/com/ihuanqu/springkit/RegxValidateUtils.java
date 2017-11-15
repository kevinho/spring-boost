package com.ihuanqu.springkit;

import java.util.regex.Pattern;

/**
 * @desc 正则表达式的验证
 */
public class RegxValidateUtils {

    private static String phoneRegx = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";

    private static String ipRegx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";


    private static String emailRegx = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

    private static String integerRegx = "^[1-9]\\d*$";


    private static String urlRegx = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";


    public static final boolean isPhone(String phone) {
        return Pattern.matches(phoneRegx, phone);
    }

    public static final boolean isIpRegx(String ip) {
        return Pattern.matches(ipRegx, ip);
    }

    public static final boolean isEmailRegx(String email) {
        return Pattern.matches(emailRegx, email);
    }

    public static final boolean isIntegerRegx(String i) {
        return Pattern.matches(integerRegx, i);
    }

    public static final boolean isUrl(String url) {
        return Pattern.matches(urlRegx, url);
    }


}