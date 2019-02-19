package net.realme.mall.oms.consts;

/**
 * Created by 91000156 on 2018/9/18 15:38
 */
public interface ProductConst {

    interface RetCode {
        // 未知错误
        Integer EXCEPTION_ERROR = 10000;
        String EXCEPTION_ERROR_MSG = "product.exception.error";
        // 服务调用失败
        Integer SERVICE_INVOKE_FAILED = 10001;
        String SERVICE_INVOKE_FAILED_MSG = "product.service.invoke.failed";
        // 接口参数校验失败
        Integer VALIDATE_FAILED = 10002;
        String VALIDATE_FAILED_MSG = "product.param.validate.failed";
        // 操作失败
        Integer OPERATED_FAILED = 10003;
        String OPERATED_FAILED_MSG = "product.operation.failed";
        // 发送失败
        Integer SEND_MSG_FAILED = 10004;
        String SEND_MSG_FAILED_MSG = "product.send.message.failed";
        // 订阅失败
        Integer SUBSCRIBE_FAILED = 10005;
        String SUBSCRIBE_FAILED_MSG = "product.subscribe.message.failed";
        // sku中的uri已重复
        Integer SKU_URI_REPEATED = 10007;
        String SKU_URI_REPEATED_MSG = "product.sku.uri.repeated";
        // 创建SKU失败
        Integer SKU_CREATE_FAILED = 10008;
        String SKU_CREATE_FAILED_MSG = "product.sku.create.failed";
        // 更新SKU失败
        Integer SKU_UPDATE_FAILED = 10009;
        String SKU_UPDATE_FAILED_MSG = "product.sku.update.failed";
        // SKU不存在
        Integer SKU_NOT_EXIST = 10010;
        String SKU_NOT_EXIST_MSG = "product.sku.not.exist";
        // SKU删除失败
        Integer SKU_DELETE_FAILED = 10011;
        String SKU_DELETE_FAILED_MSG = "product.sku.delete.failed";
        // 创建SPU失败
        Integer SPU_CREATE_FAILED = 10012;
        String SPU_CREATE_FAILED_MSG = "product.spu.create.failed";
        // 创建SPU失败
        Integer SPU_UPDATE_FAILED = 10013;
        String SPU_UPDATE_FAILED_MSG = "product.spu.update.failed";
        // SPU不存在
        Integer SPU_NOT_EXIST = 10014;
        String SPU_NOT_EXIST_MSG = "product.spu.not.exist";
        // SKU删除失败
        Integer SPU_DELETE_FAILED = 10015;
        String SPU_DELETE_FAILED_MSG = "product.spu.delete.failed";
    }
}
