package net.realme.mall.store.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.web.controller.BaseController;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.mall.product.domain.response.SkuLiveStatusResponse;
import net.realme.mall.product.facade.ProductDetailService;
import net.realme.mall.store.common.consts.NotificationConst;
import net.realme.mall.store.common.consts.StoreProductConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by 91000156 on 2018/9/13 18:16
 */
@Api(tags = {"商品信息"})
@Validated
@RestController
public class ProductController extends LocalizeController {

    @Autowired
    private ProductDetailService productDetailService;

    @ApiOperation(value = "获取最新sku状态", notes = "销售状态(saleStatus):0-不开卖、1-开卖、2-预约,库存状态(stockStatus):0-没库存,1-有库存,服务器的当前时间(serverNowTime),开卖开始的时间(saleStartTime),倒计时(countdownWithin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品SkuId", paramType = "query", required = true),
    })
    @GetMapping("/product/status")
    public Result getSkuLiveStatus(@Min(1) Integer skuId) {
        // 参数skuId参数判断
        if (skuId == null) {
            return errI18N(NotificationConst.RetCode.VALIDATE_FAILED, NotificationConst.RetCode.VALIDATE_FAILED_MSG);
        }
        try {
            // 获取商品的实时状态
            SkuLiveStatusResponse liveStatusResponse = productDetailService.getSkuLiveStatusInfo(skuId);
            return ok(liveStatusResponse);
        } catch (Exception e) {
            logger.error("getSkuLiveStatus throws", e);
            return errI18N(StoreProductConst.ReturnCode.LIVE_STATUS_ERROR, StoreProductConst.ReturnCode.LIVE_STATUS_ERROR_MSG);
        }
    }
}
