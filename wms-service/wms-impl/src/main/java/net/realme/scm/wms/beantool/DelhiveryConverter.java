package net.realme.scm.wms.beantool;

import net.realme.framework.util.time.TimeUtil;
import net.realme.framework.util.time.TimeZoneConstant;
import net.realme.mall.order.domain.OrderDeliveryDto;
import net.realme.mall.order.domain.OrderItemDto;
import net.realme.mall.order.domain.OrderMainDto;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.scm.wms.domain.PushProductPayload;
import net.realme.scm.wms.domain.delhivery.*;
import net.realme.scm.wms.model.ProductCreateHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.beantool
 *
 * @author 91000044
 * @date 2018/9/19 21:40
 */
@Mapper(componentModel = "spring")
public abstract class DelhiveryConverter {

    @Value("${wms.delhivery.account.2c.accessKey}")
    private String accessKey;
    @Value("${wms.delhivery.account.2c.clientKey}")
    private String clientKey;
    @Value("${wms.delhivery.account.2c.supplierKey}")
    private String supplierKey;

    private static final String GODAM_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public DelhiveryOrder toWmsOrder(OrderMainDto orderMainDto) {
        DelhiveryOrder delhiveryOrder = new DelhiveryOrder();
        OrderDeliveryDto orderDeliveryDto = orderMainDto.getDelivery();

        delhiveryOrder.setOrderNumber(String.valueOf(orderMainDto.getOrderNo()));
        if (orderMainDto.getCreateTime() != null) {
            delhiveryOrder.setOrderDate(TimeUtil.timestampToLocalStr(orderMainDto.getCreateTime(),
                    GODAM_TIME_FORMAT,
                    TimeZoneConstant.ZONE_INDIA));
        } else {
            delhiveryOrder.setOrderDate(TimeUtil.nowToLocalStr(
                    GODAM_TIME_FORMAT,
                    TimeZoneConstant.ZONE_INDIA));
        }

        //收货人信息
        DelhiveryConsignee delhiveryConsignee = new DelhiveryConsignee();
        delhiveryConsignee.setCountry("India");
        delhiveryConsignee.setName(orderDeliveryDto.getFullName());
        delhiveryConsignee.setState(orderDeliveryDto.getProvince());
        delhiveryConsignee.setCity(orderDeliveryDto.getCity());
        delhiveryConsignee.setAddress1(orderDeliveryDto.getAddress1());
        delhiveryConsignee.setAddress2(orderDeliveryDto.getAddress2());
        delhiveryConsignee.setPhone1(Long.parseLong(orderDeliveryDto.getPhone()));
        delhiveryConsignee.setPincode(Long.parseLong(orderDeliveryDto.getPinCode()));
        delhiveryOrder.setConsignee(delhiveryConsignee);
        //商品信息
        List<OrderItemDto> items = orderMainDto.getItems();

        for (OrderItemDto itemDto : items) {
            DelhiverySubOrder subOrder = new DelhiverySubOrder();

            //todo allocate warehouse ?
            subOrder.setFulfillmentCenter("FCDEL1");
            subOrder.setAccessKey(accessKey);
            subOrder.setSubOrderNumber(itemDto.getOrderNo());

            DelhiveryProductDetails productDetails = new DelhiveryProductDetails();
            productDetails.setHsnCode(itemDto.getErpCode());
            productDetails.setName(itemDto.getSkuName());
            productDetails.setSku(String.valueOf(itemDto.getSkuId()));
            productDetails.setNumber(itemDto.getErpCode());
            productDetails.setQuantity(itemDto.getSkuCount());
            subOrder.setProductDetails(productDetails);

            DelhiveryShipmentDetails shipmentDetails = new DelhiveryShipmentDetails();
            //todo get payment mode from order
            shipmentDetails.setPaymentMode("cod");
            subOrder.setShipmentDetails(shipmentDetails);

            DelhiveryInvoiceDetails invoiceDetails = new DelhiveryInvoiceDetails();
            invoiceDetails.setInvoiceDate(TimeUtil.nowToLocalStr(
                    GODAM_TIME_FORMAT,
                    TimeZoneConstant.ZONE_INDIA));
            subOrder.setInvoiceDetails(invoiceDetails);

            if (delhiveryOrder.getSubOrders() == null || delhiveryOrder.getSubOrders().isEmpty()) {
                delhiveryOrder.setSubOrders(new ArrayList<>());
            }
            delhiveryOrder.getSubOrders().add(subOrder);
        }

        return delhiveryOrder;
    }

    public DelhiveryProduct toDelhiveryProduct(ProductSkuResponse response) {
        DelhiveryProduct delhiveryProduct = new DelhiveryProduct();

        delhiveryProduct.setClientKey(clientKey);
        delhiveryProduct.setSupplierKey(supplierKey);
        delhiveryProduct.setNumber(response.getEanCode());
        delhiveryProduct.setName(response.getSkuName());
        delhiveryProduct.setSku(String.valueOf(response.getSkuId()));
        delhiveryProduct.setDescription(response.getDescription());
        delhiveryProduct.setWeight(response.getPackWeight().intValue());
        delhiveryProduct.setWidth(response.getPackWidth().intValue());
        return delhiveryProduct;
    }

    public ProductCreateHistory toProductCreateHistroy(PushProductPayload pushProductPayload) {
        DelhiveryProduct delhiveryProduct = (DelhiveryProduct) pushProductPayload;

        ProductCreateHistory productCreateHistory = new ProductCreateHistory();
        productCreateHistory.setCreatedAt(System.currentTimeMillis());
        productCreateHistory.setSku(delhiveryProduct.getSku());
        productCreateHistory.setSkuName(delhiveryProduct.getName());
        productCreateHistory.setNumber(delhiveryProduct.getNumber());
        productCreateHistory.setVendor("delhivery");
        productCreateHistory.setStatus((byte)1);
        return productCreateHistory;
    }

    public  List<ProductCreateHistory> toProductCreateHistroyList(List<? extends PushProductPayload> delhiveryProducts){
        List<ProductCreateHistory> productCreateHistories=new ArrayList<>();
        List<ProductCreateHistory> collect = delhiveryProducts.stream().map(item -> {
            ProductCreateHistory productCreateHistory = new ProductCreateHistory();
            productCreateHistory = this.toProductCreateHistroy(item);
            return productCreateHistory;
        }).collect(Collectors.toList());
        return collect;
    }

/*    public abstract List<DelhiveryProduct> toDelhiveryProductList(List<ProductCreateHistory> productCreateHistories);

    @Mappings({
            @Mapping(source = "sku", target = "sku"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "skuName", target = "name")
    })
    public abstract DelhiveryProduct toDelhiveryProduct(List<ProductCreateHistory> productCreateHistories);*/
}
