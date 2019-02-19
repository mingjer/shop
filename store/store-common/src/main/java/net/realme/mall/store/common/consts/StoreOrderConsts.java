package net.realme.mall.store.common.consts;

/**
 * @author 91000182
 * @date 2018/9/3
 */
public interface StoreOrderConsts {

    public interface ErrorInfo {
//        int NOT_LOGIN_CODE = 20001;
//        String NOT_LOGIN_MSG ="not login.";
        int INSUFFICIENT_INV_CODE = 20002;
        String INSUFFICIENT_INV_MSG ="insufficient.inv.msg";
        int UNREACHABLE_CODE = 20003;
        String UNREACHABLE_MSG ="unreachable.msg";
        int SKU_NOT_FOUND_CODE = 20004;
        String SKU_NOT_FOUND_MSG ="sku.not.found.msg";
        int SKU_PRICE_INVALID_CODE = 20005;
        String SKU_PRICE_INVALID_MSG ="sku.price.invalid.msg";
        int SKU_NUM_UP_LIMIT_CODE = 20006;
        String SKU_NUM_UP_LIMIT_MSG ="sku.num.up.limit.msg";
        int ORDER_UP_LIMIT_CODE = 20007;
        String ORDER_UP_LIMIT_MSG ="order.up.limit.msg";
        int SKU_ID_NULL_CODE = 20101;
        String SKU_ID_NULL_MSG ="sku.id.null.msg";
        int PRICE_NULL_CODE = 20102;
        String PRICE_NULL_MSG ="price.null.msg";
        int COUNT_ZERO_CODE = 20103;
        String COUNT_ZERO_MSG ="count.zero.msg";
        int NON_PURCHASE_PERIOD_CODE = 20104;
        String NON_PURCHASE_PERIOD_MSG ="non.purchase.period.msg";
        int OFF_SHELF_STATUS_CODE = 20105;
        String OFF_SHELF_STATUS_MSG ="off.shelf.status.msg";
        int NOT_SALE_STATUS_CODE = 20106;
        String NOT_SALE_STATUS_MSG ="not.sale.status.msg";
        int PLACE_ORDER_ITEM_NULL_CODE = 20107;
        String PLACE_ORDER_ITEM_NULL_MSG ="place.order.item.null.msg";
        int PLACE_ORDER_ADDRESS_NULL_CODE = 20108;
        String PLACE_ORDER_ADDRESS_NULL_MSG ="place.order.address.null.msg";
        int CANCEL_ORDER_FAIL_CODE = 20109;
        String CANCEL_ORDER_FAIL_MSG ="cancel.order.fail.msg";
        int GET_ORDER_FAIL_CODE = 20111;
        String GET_ORDER_FAIL_MSG ="get.order.fail.msg";
        int BUY_ORDER_ITEM_EMPTY_CODE = 20112;
        String BUY_ORDER_ITEM_EMPTY_MSG ="buy.order.item.empty.msg";
        int ORDER_STAGE_ERR_CODE = 20113;
        String ORDER_STAGE_ERR_MSG ="order.stage.err.msg";
        int GET_ORDER_ITEMS_ERR_CODE = 20114;
        String GET_ORDER_ITEMS_ERR_MSG ="get.order.items.err.msg";
        int NO_STAGE_ITEMS_ERR_CODE = 20115;
        String NO_STAGE_ITEMS_ERR_MSG ="no.stage.items.err.msg";
        int ADDRESS_NULL_CODE = 20116;
        String ADDRESS_NULL_MSG ="address.null.msg";
        int ORDER_CREATE_ERR_CODE = 20117;
        String ORDER_CREATE_ERR_MSG ="order.create.err.msg";
        int ORDER_POLLING_ERR_CODE = 20118;
        String ORDER_POLLING_ERR_MSG ="order.polling.err.msg";
        int ORDER_QUERY_ERR_CODE = 20119;
        String ORDER_QUERY_ERR_MSG ="order.query.err.msg";
        int ORDER_CANCEL_ERR_CODE = 20120;
        String ORDER_CANCEL_ERR_MSG ="order.cancel.err.msg";
        int ORDER_NOT_YET_CREATED_CODE = 20121;
        String ORDER_NOT_YET_CREATED_MSG ="order.not.yet.created.msg";
        int GET_ORDER_PAYED_ITEMS_ERR_CODE = 20122;
        String GET_ORDER_PAYED_ITEMS_ERR_MSG ="get.order.payed.items.err.msg";
        int ORDER_CHECKOUT_EXPIRED_CODE = 20123;
        String ORDER_CHECKOUT_EXPIRED_MSG ="order.checkout.expired.msg";
        int ORDER_PRICE_INVALID_CODE = 20124;
        String ORDER_PRICE_INVALID_MSG ="order.price.invalid.msg";
        int ORDER_CREATED_INSUFFICIENT_INV_CODE = 20125;
        String ORDER_CREATED_INSUFFICIENT_INV_MSG ="order.created.insufficient.inv.msg";
        int SKU_INV_ZERO_CODE = 20126;
        String SKU_INV_ZERO_MSG ="sku.inv.zero.msg";
        int ORDER_CREATE_SEND_MQ_ERR_CODE = 20127;
        String ORDER_CREATE_SEND_MQ_ERR_MSG ="order.create.send.mq.err.msg";
        int USER_PROD_DAY_LIMIT_CODE = 20128;
        String USER_PROD_DAY_LIMIT_MSG ="user.prod.day.limit.msg";
        int REQUEST_FREQUENT_LIMIT_CODE = 20129;
        String REQUEST_FREQUENT_LIMIT_MSG ="request.frequent.limit";

    }

    // redis缓存
    public interface ReidsCache {
        int MINUTE_30 = 30*60;
        int MINUTE_10 = 10*60;
    }

    // 商品类型
    public interface SkuType {
        Integer NORMAL = 1;// 普通商品
        Integer ACCESSORY =2;// 配件
    }

    // 订单每单上限
    int ORDER_UP_LIMIT = 1;
    // 订单上限缓存key
    String ORDER_UP_LIMIT_CACHE_KEY = "order:up:limit:num";
}
