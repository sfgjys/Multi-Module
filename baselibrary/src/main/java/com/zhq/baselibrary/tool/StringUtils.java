package com.zhq.baselibrary.tool;

public class StringUtils {

    /**
     * 方法描述: 给0到9的个位数前面加个0
     */
    public static String lessThanNineConvertString(int lessThanNine) {
        return (lessThanNine >= 0 && lessThanNine <= 9) ? ("0" + lessThanNine) : (lessThanNine + "");
    }
}
