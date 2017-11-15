package com.ihuanqu.springkit;

/**
 * Created by changbinhe on 2017/3/14.
 */
public class EnumUtils {

    public static <T extends Enum<T>> boolean isValidEnumOrdinal(Class<T> enums, int ordinal) {
        T[] a = enums.getEnumConstants();
        for (T m : a) {
            if (m.ordinal() == ordinal) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Enum<T>> boolean matchOrdinal(T a, int ordinal) {
        return a.ordinal() == ordinal;
    }

    public static <T extends Enum<T>> T findEnumConstant(Class<T> enums, int ordinal) {
        T[] a = enums.getEnumConstants();
        for (T m : a) {
            if (m.ordinal() == ordinal) {
                return m;
            }
        }
        return null;
    }

}
