package net.realme.framework.util.text;

import org.apache.commons.lang3.StringUtils;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.text
 *
 * @author 91000044
 * @date 2018/9/7 19:13
 */
public class UrlUtil {

    public static String join(String base, String... pathWhatever) {
        StringBuilder stringBuilder = new StringBuilder(StringUtils.stripEnd(base, "/"));
        for (String p : pathWhatever) {
            p = StringUtils.stripStart(p, "/");
            if (StringUtils.isNotBlank(p)) {
                stringBuilder.append("/").append(p);
            }
        }
        return stringBuilder.toString();
    }
}
