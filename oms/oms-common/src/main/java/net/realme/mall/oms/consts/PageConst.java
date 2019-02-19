package net.realme.mall.oms.consts;

/**
 * Created by 91000156 on 2018/9/19 18:01
 */
public interface PageConst {

    interface RetCode {
        // 预览页面错误
        Integer PREVIEW_ERR = 11001;
        String PREVIEW_ERR_MSG = "product.detail.preview.error";
        // 发布页面错误
        Integer DEPLOY_ERR = 11002;
        String DEPLOY_ERR_MSG = "product.detail.deploy.error";
        // 发布页面时异常
        Integer DEPLOY_EXCEPTION = 11003;
        String DEPLOY_EXCEPTION_MSG = "product.detail.deploy.exception";
    }

    // 1：www,2:buy
    interface DomainType {
        Byte DOMAIN_WWW = 1;
        Byte DOMAIN_BUY = 2;
    }

    // 状态：0-上架，1-已下架
    interface ShelfStatus {
        Byte UP = 0;
        Byte DOWN = 1;
    }
}
