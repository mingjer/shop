package net.realme.mall.oms.cms.facade;

/**
 * Created by 91000156 on 2018/9/6 11:00
 */
public interface ProductDetailPageService {

    byte STATUS_INPROGRESS = 0;
    byte STATUS_RELEASED = 1;
    byte STATUS_FAILED = 2;

    // 发布页面前插入一条发布记录
    int releaseStart(int skuId, int pageId, String siteCode, long version);

    // 发布成功之后更新发布记录
    int releaseEnd(int skuId, long version, String htmlContent);
}
