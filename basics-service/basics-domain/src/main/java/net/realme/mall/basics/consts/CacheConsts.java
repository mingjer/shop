package net.realme.mall.basics.consts;

/**
 * redis缓存信息
 */
public interface CacheConsts {

    // 根据siteCode存放货币信息
    String SITE_CODE_CURRENCY = "siteCode:currency:%s";

    // 根据siteCode存放site信息
    String SITE_CODE_SITE = "siteCode:site:%s";
}
