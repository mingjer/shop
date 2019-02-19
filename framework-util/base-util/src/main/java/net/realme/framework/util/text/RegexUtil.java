package net.realme.framework.util.text;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.text
 *
 * @author 91000044
 * @date 2018/7/25 19:18
 */
public class RegexUtil {

    /**
     * 正则表达式：验证邮箱合法性
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证国际手机号码
     */
    public static final String REGEX_CHINA_MOBILE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
    /**
     * 正则表达式：验证国际手机号码
     */
    public static final String REGEX_INTERNATIONAL_MOBILE = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$";

    public static boolean isEmail(String emailString) {
        if (StringUtils.isBlank(emailString)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher m = p.matcher(emailString);
        return m.matches();
    }

    public static boolean isPhone(String phoneNumber) {
        return StringUtils.isNoneBlank(phoneNumber) && isChinaPhone(phoneNumber);
    }

    public static boolean isChinaPhone(String phoneNumber) {
        Pattern p = Pattern.compile(REGEX_CHINA_MOBILE);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 校验手机号码（国际号码）是否合法
     *
     * @param number
     * @return
     */
    public static boolean isInternationalPhone(String number){
        if (StringUtils.isBlank(number)) {
            return false;
        }
        Pattern regex = Pattern.compile(REGEX_INTERNATIONAL_MOBILE);
        Matcher matcher = regex.matcher(number);
        return matcher.matches();
    }
}
