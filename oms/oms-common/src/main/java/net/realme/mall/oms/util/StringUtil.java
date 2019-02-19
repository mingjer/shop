package net.realme.mall.oms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 91000156 on 2018/9/1 14:09
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !(str == null || str.length() == 0);
    }

    public static boolean existSpaceHeadOrTail(String str) {
        return str.length() != str.trim().length();
    }

    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
