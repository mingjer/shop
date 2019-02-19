package net.realme.scm.wms.delhivery.controller;

import net.realme.framework.util.time.TimeUtil;
import net.realme.framework.util.time.TimeZoneConstant;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.scm.inventory.domain.SkuLogisticInventoryDto;
import net.realme.scm.inventory.facade.SkuLogisticInventoryService;
import net.realme.scm.wms.domain.WmsShippingNotifyDto;
import net.realme.scm.wms.domain.delhivery.DelhiveryInventoryNotification;
import net.realme.scm.wms.domain.delhivery.DelhiveryOrderNotification;
import net.realme.scm.wms.domain.delhivery.DelhiveryOrderNotification.NotifiedOrderLines;
import net.realme.scm.wms.domain.delhivery.DelhiveryOrderNotification.NotifiedProduct;
import net.realme.scm.wms.domain.delhivery.DelhiveryOrderStatus;
import net.realme.scm.wms.domain.delhivery.DelhiveryResponse;
import net.realme.scm.wms.facade.WmsShippingNotifyService;
import net.realme.scm.wms.impl.DelhiveryIntegrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DelhiveryNotifyController extends LocalizeController {

    @Resource(name = "delhivery")
    private DelhiveryIntegrationImpl delhiveryIntegration;
    @Autowired
    private SkuLogisticInventoryService skuLogisticInventoryService;
    @Autowired
    private WmsShippingNotifyService wmsShippingNotifyService;

    /**
     * 收货通知
     *
     * @param siteCode
     * @param provider
     * @param body
     * @return
     */
    @PostMapping("/wms/{provider}/inbound")
    public DelhiveryResponse inboundNotify(@PathVariable String siteCode,
                                             @PathVariable String provider,
                                             @RequestBody String body) {
        return new DelhiveryResponse();
    }


    /**
     * 订单通知， 打包、发货等
     *
     * @param siteCode
     * @param provider
     * @param orderNotification
     * @return
     */
    @PostMapping("/wms/{provider}/order")
    public DelhiveryResponse orderNotify(@PathVariable String siteCode,
                                         @PathVariable String provider,
                                         @RequestBody DelhiveryOrderNotification orderNotification) {
        logger.info("notification: {}", orderNotification);
        List<NotifiedOrderLines> orderLines = orderNotification.getOrderlines();
        List<WmsShippingNotifyDto> wmsShippingNotifyDtos=new ArrayList<>();
        for (NotifiedOrderLines orderLines1: orderLines) {
            if(DelhiveryOrderStatus.PAK.getValue().equals(orderLines1.getStatus())) {

            } else if(DelhiveryOrderStatus.SHP.getValue().equals(orderLines1.getStatus())) {
                String orderNo = orderLines1.getOrderId();
                String fulfillmentCenter = orderLines1.getFulfillmentCenter();
                String invoiceUrl = orderLines1.getInvoiceDetails().getInvoiceUrl();
//                long deliveredAt = TimeUtil.localStrToTimestamp(orderLines1.getShippingDate(), "yyyy-MM-dd'T'HH:mm:ssZ'", TimeZoneConstant.ZONE_INDIA);
                String deliveredAt = orderLines1.getShippingDate().toString();
                String waybill = orderLines1.getWaybill();
                List<NotifiedProduct> productList = orderLines1.getProducts();

                WmsShippingNotifyDto wmsShippingNotifyDto= new WmsShippingNotifyDto();
                wmsShippingNotifyDto.setCreatedAt(System.currentTimeMillis());
                wmsShippingNotifyDto.setDeliveredAt(String.valueOf(deliveredAt));
                wmsShippingNotifyDto.setFulfillmentCenter(fulfillmentCenter);
                wmsShippingNotifyDto.setWaybill(waybill);
                wmsShippingNotifyDto.setOrderNo(orderNo);
                wmsShippingNotifyDto.setVendor("delhivery");
                wmsShippingNotifyDto.setInvoiceUrl(invoiceUrl);

                wmsShippingNotifyDtos.add(wmsShippingNotifyDto);
            }
        }
        wmsShippingNotifyService.batchInsert(wmsShippingNotifyDtos);
        return new DelhiveryResponse();
    }

    /**
     * 实时库存通知
     *
     * @param siteCode
     * @param provider
     * @param inventoryNotification
     * @return
     */
    @RequestMapping("/wms/{provider}/inventory")
    public DelhiveryResponse inventoryNotify(@PathVariable String siteCode,
                                             @PathVariable String provider,
                                             @RequestBody DelhiveryInventoryNotification inventoryNotification) {
        logger.info("notification: {}", inventoryNotification);
        SkuLogisticInventoryDto skuLogisticInventoryDto = new SkuLogisticInventoryDto();

        Map<String, SkuLogisticInventoryDto> skuLogisticInventoryDtoMap = new HashMap<>(50);

        for (DelhiveryInventoryNotification.NotifiedInventory notifiedInventory: inventoryNotification.getInventory()) {
            skuLogisticInventoryDto.setSiteCode(siteCode);
            skuLogisticInventoryDto.setEanCode(notifiedInventory.getSku());
            skuLogisticInventoryDto.setPhysicalQuantity(notifiedInventory.getQty());
            skuLogisticInventoryDto.setWarehouseCode(notifiedInventory.getFulfillmentCenterName());
            skuLogisticInventoryDtoMap.put(skuLogisticInventoryDto.getEanCode(), skuLogisticInventoryDto);
        }

        skuLogisticInventoryService.updatePhysical(skuLogisticInventoryDtoMap);

        return new DelhiveryResponse();
    }

}

