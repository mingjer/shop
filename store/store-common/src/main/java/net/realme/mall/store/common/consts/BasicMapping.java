package net.realme.mall.store.common.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础数据映射
 */
public class BasicMapping {
    public static final Map<String,String> countryMap =new HashMap<String, String>() {
        {
            put("in", "India");
        }
    };

    public static final Map<String,String> currencySymbolMap =new HashMap<String, String>() {
        {
            put("in", "₹");
        }
    };
}
