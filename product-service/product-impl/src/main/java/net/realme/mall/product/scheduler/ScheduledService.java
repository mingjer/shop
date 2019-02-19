package net.realme.mall.product.scheduler;

import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.facade.ProductSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/8 18:20
 */
@Component
public class ScheduledService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductSkuService productSkuService;

    /**
     * 每隔5秒检查
     * 1.sku信息表中为预约状态中的sku是否预约时间已到
     * 2.sku信息表中为开卖状态的sku是否已到开卖结束时间
     *
     * 销售状态 0 不开卖 1 开卖 2 预约
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void updateSkuSaleStatus(){
        /** 1·将为预约状态且已到开卖开始时间的sku改为开卖状态 **/
        List<ProductSkuDto> reserveSkuList = productSkuService.getSkuInfoBySaleStatus(ProductConst.SaleStatus.RESERVE);
        if (reserveSkuList != null && !reserveSkuList.isEmpty()) {
            // 获取当前的时间戳
            Long nowTime = System.currentTimeMillis();
            for (ProductSkuDto sku : reserveSkuList) {
                // 判断当前时间是否大于开卖时间
                if (sku.getSaleStart() != null && nowTime >= sku.getSaleStart()) {
                    logger.info("scheduler update sku [{}] sale_status to sale :", sku.getSkuId());
                    ProductSkuDto productSkuDto = new ProductSkuDto();
                    productSkuDto.setSkuId(sku.getSkuId());
                    // 将状态更改为开卖状态
                    productSkuDto.setSaleStatus(ProductConst.SaleStatus.SALE);
                    int result = productSkuService.updateSkuInfo(productSkuDto).getData();
                    if (result > 0) {
                        logger.info("scheduler update sku [{}] sale_status to sale update success", sku.getSkuId());
                    }
                }
            }
        }
        /** 2·将为开卖状态且已到售卖结束时间的sku改为不开卖状态 **/
        List<ProductSkuDto> saleSkuList = productSkuService.getSkuInfoBySaleStatus(ProductConst.SaleStatus.SALE);
        if (saleSkuList != null && !saleSkuList.isEmpty()) {
            // 获取当前的时间戳
            Long currentTime = System.currentTimeMillis();
            for (ProductSkuDto sku : saleSkuList) {
                // 判断当前时间是否大于开卖的结束时间
                if (sku.getSaleStart() != null && currentTime >= sku.getSaleEnd()) {
                    logger.info("scheduler update sku [{}] sale_status to not_sale : ", sku.getSkuId());
                    ProductSkuDto productSkuDto = new ProductSkuDto();
                    productSkuDto.setSkuId(sku.getSkuId());
                    // 将状态更改为不开卖状态
                    productSkuDto.setSaleStatus(ProductConst.SaleStatus.NOT_SALE);
                    int result = productSkuService.updateSkuInfo(productSkuDto).getData();
                    if (result > 0) {
                        logger.info("scheduler update sku [{}] sale_status to not_sale update success", sku.getSkuId());
                    }
                }
            }
        }
    }
}
