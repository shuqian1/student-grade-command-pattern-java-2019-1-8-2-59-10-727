package com.tw;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by QinShuqian on 2019/4/3.
 */
public class Util {
    public static String replaceZero(double num){
        return new BigDecimal(String.valueOf(num)).stripTrailingZeros().toPlainString();
    }

    //    判断是否为数字
    public static boolean isNum(String num){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isnum = pattern.matcher(num);
        return isnum.matches();
    }
}
