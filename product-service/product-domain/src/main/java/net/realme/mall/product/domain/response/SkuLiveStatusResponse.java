package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/13 15:45
 */
public class SkuLiveStatusResponse implements Serializable {

    private static final long serialVersionUID = 1488367533839333809L;

    // 状态：0-上架，1-已下架(下架后跳转首页)
    private Byte shelfStatus;
    // 销售状态 0 不开卖 1 开卖 2 预约
    private Byte saleStatus;
    // 库存状态 0 没库存 1 有库存
    private String stockStatus;
    // 服务器的当前时间
    private Long serverNowTime;
    // 开卖开始的时间
    private Long saleStartTime;
    // 获取剩余分钟数开始倒计时
    private String countdownWithin;

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Byte getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Long getServerNowTime() {
        return serverNowTime;
    }

    public void setServerNowTime(Long serverNowTime) {
        this.serverNowTime = serverNowTime;
    }

    public Long getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Long saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public String getCountdownWithin() {
        return countdownWithin;
    }

    public void setCountdownWithin(String countdownWithin) {
        this.countdownWithin = countdownWithin;
    }
}
