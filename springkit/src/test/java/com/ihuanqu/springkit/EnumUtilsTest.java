package com.ihuanqu.springkit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by changbinhe on 2017/3/14.
 */
public class EnumUtilsTest {
    @Test
    public void isValidEnumOrdinal() throws Exception {
        Assert.assertEquals(true, EnumUtils.isValidEnumOrdinal(TestEnum.class, 0));
        Assert.assertEquals(false, EnumUtils.isValidEnumOrdinal(TestEnum.class, 2));
    }

    @Test
    public void findEnumConstant() throws Exception {
        Assert.assertEquals(TestEnum.AB, EnumUtils.findEnumConstant(TestEnum.class, 0));
    }

    enum TestEnum {
        AB, CD,;
    }
}