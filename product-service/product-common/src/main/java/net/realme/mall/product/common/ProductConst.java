package net.realme.mall.product.common;

/**
 * Created by 91000156 on 2018/9/13 17:51
 */
public interface ProductConst {

    interface SaleStatus {
        // 销售状态 0 不开卖 1 开卖 2 预约
        Byte NOT_SALE = 0;
        Byte SALE = 1;
        Byte RESERVE = 2;
    }
    interface DataStatus {
        // 状态：0 正常，-1 已删除
        Byte NORMAL = 0;
        Byte DELETED = -1;
    }

    interface RedisKey {
        // SPU 缓存Key
        String CACHE_SPU_INFO_KEY = "m:prd_spu:";
        // SPU 缓存Key
        String CACHE_SKU_INFO_KEY = "m:prd_sku:";
        // SKU缓存有效时间，秒
        Long SKU_CACHE_TTL = 120L;
        // SPU缓存有效时间，秒
        Long SPU_CACHE_TTL = 120L;
    }

    interface SkuStockStatus {
        // 1 有库存
        String IN_STOCK = "1";
        // 0 没有库存
        String OUT_STOCK = "0";
    }

    interface AttrInfo {
        // 颜色的属性值为1
        Integer COLOR_ATTR_ID = 1;
        // 配置的属性值为2
        Integer SPEC_ATTR_ID = 2;

        String COLOR_ATTR_ID_STR = "1";
        String SPEC_ATTR_ID_STR = "2";
    }
}
