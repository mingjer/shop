package net.realme.scm.wms.task;

import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.time.TimeUtil;
import net.realme.framework.util.time.TimeZoneConstant;
import net.realme.mall.order.domain.OrderMainDto;
import net.realme.mall.order.facade.OrderService;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.facade.ProductSkuService;
import net.realme.scm.wms.beantool.DelhiveryConverter;
import net.realme.scm.wms.domain.delhivery.DelhiveryOrder;
import net.realme.scm.wms.domain.delhivery.DelhiveryProduct;
import net.realme.scm.wms.facade.ProductCreateHistoryService;
import net.realme.scm.wms.impl.DelhiveryIntegrationImpl;
import net.realme.scm.wms.model.ProductCreateHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 批量定时推送订单信息给delhivery
 *
 * @author 91000044
 */
@Component
public class DelhiveryOrderPushTask {

    private static final int UNIT_PUSH = 100;
    private static final int UNIT_PRODUCT = 100;
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    @Autowired
    private OrderService orderService;

    @Autowired
    private DelhiveryConverter delhiveryConverter;

    @Resource(name = "delhivery")
    private DelhiveryIntegrationImpl delhiveryIntegration;

    @Autowired
    private ProductSkuService productSkuService;
   private static Logger logger = LoggerFactory.getLogger(DelhiveryOrderPushTask.class);

    /**
     * 定时推送订单
     */
    @Scheduled(cron = "* */5 * * * ?")
    public void pushOrderTask() {
        if (atomicBoolean.get()) {
            logger.info("placeOrderAction () 定时任务正在执行currentDateTime={}", new Date().toString());
            return;
        }
        List<OrderMainDto> orderMainDtoList = orderService.getPaidOrderInfo(UNIT_PUSH).getData();
        if (orderMainDtoList == null || orderMainDtoList.isEmpty()) {
            return;
        }
        List<DelhiveryOrder> delhiveryOrders = new ArrayList<>(UNIT_PUSH);

        List<Long> ids = new ArrayList<>(UNIT_PUSH);
        // 拼接订单信息
        orderMainDtoList.forEach(item -> {
            DelhiveryOrder delhiveryOrder = delhiveryConverter.toWmsOrder(item);
            delhiveryOrders.add(delhiveryOrder);
        });

        delhiveryIntegration.pushOrder(delhiveryOrders);
        atomicBoolean.set(true);

        //todo
        //批量更新订单在运输中
//        orderService.updateOrderStatus(OrderConsts.OrderStatus.DELIVERYING, ids);

    }

    /**
     * 定时推送sku任务
     */
@Autowired
private ProductCreateHistoryService productCreateHistoryService;
    @Scheduled(cron = "* * */1 * * ?")
    public void pushProductTask() {
        //获取sku列表
        List<ProductSkuResponse> productSkuResponses = productSkuService.getCreateSkuList(UNIT_PRODUCT).getData();
        List<DelhiveryProduct> delhiveryProducts = new ArrayList<>();
        delhiveryProducts = productSkuResponses.stream()
                .map(item -> {
                            DelhiveryProduct delhiveryProduct = new DelhiveryProduct();
                            delhiveryProduct = delhiveryConverter.toDelhiveryProduct(item);
                            return delhiveryProduct;
                        }
                ).collect(Collectors.toList());

        //过滤历史推送sku
        List<DelhiveryProduct> productFilterHistoory= productCreateHistoryService.filterHistroy(delhiveryProducts);

        ResultT<Boolean> booleanResultT = delhiveryIntegration.pushProductList(productFilterHistoory);
        Integer i=0;
        if (productFilterHistoory.size() > 0) {
            i = productCreateHistoryService.batchInsert(productFilterHistoory);
        }
        if (i < 1) {
            logger.info("pushProductTask插入为0");
        }
    }

}
