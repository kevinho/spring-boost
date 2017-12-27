package io.kvh.springkit;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author kvh
 * @date 2017/8/23
 */
public class ConcurrencyKit {

    /**
     * 将单位为分的转换为元
     * @param fen
     * @return
     */
    public static float fenToYuan(int fen) {
        DecimalFormat format = new DecimalFormat("#.##");
        return Float.valueOf(format.format(fen / 100.0));
    }

    /**
     * 将单位为元转换为单位为分
     *
     * @param yuan 将要转换的元的数值字符串
     */
    public static Integer yuanToFen(String yuan) {
        return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }
}
