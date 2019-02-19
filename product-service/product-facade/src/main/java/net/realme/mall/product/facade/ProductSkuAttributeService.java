package net.realme.mall.product.facade;

import net.realme.mall.product.domain.ProductSkuAttributeDto;
import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;
import net.realme.mall.product.domain.response.DefaultOptionRetModel;
import net.realme.mall.product.domain.response.ProductSkuAttributeResponse;
import net.realme.mall.product.domain.response.SkuAttributeInSkuResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by 91000156 on 2018/8/29 15:26
 */
public interface ProductSkuAttributeService {

    // sku属性组字段中的分割符
    String idsSeparator = ";";

    // 添加sku商品对应的sku属性组合
    int addSkuAttribute(List<ProductSkuAttributeDto> attributeDtoList, Integer productId, Integer skuId);

    // 根据productId或者skuId查询sku属性组合
    Map<String,String> getSkuAttribute(Integer skuId);

    List<ProductSkuAttributeResponse> getSkuAttrResponse(Integer skuId);

    List<ProductSkuAttributeRequest> getSkuAttrRequest(Integer skuId);

    // 根据product-id获取此商品下所有已选的sku属性组合
    Map<String, List<DefaultOptionRetModel>> getProductSkuAttr(Integer productId);

    // 根据skuId删除此sku已选的属性组合
    int deleteSkuAttribute(Integer skuId);

    // 获取Sku返回值中的属性组
    List<SkuAttributeInSkuResponse> getSkuAttrInSkuResponse(Integer skuId);

    // 更新sku属性的上下架状态
    int updateSkuAttributeShelfStatus(Integer skuId, byte shelfStatus);
}
