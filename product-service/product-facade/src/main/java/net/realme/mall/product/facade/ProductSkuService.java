package net.realme.mall.product.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.domain.SkuListQuery;
import net.realme.mall.product.domain.request.SpuInfoOnSkuRequest;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.domain.response.SelSkuAttrGroupResponse;
import net.realme.mall.product.domain.response.GetSkuAttrGroupResponse;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 13:51
 */
public interface ProductSkuService {

    // 添加SKU信息
    ResultT<Integer> addSkuInfo(ProductSkuDto productSkuDto);

    // 根据SKU-ID获取SKU信息
    ResultT<ProductSkuResponse> getSkuInfoById(Integer skuId);

    // 更新商品SKU的上架、下架状态信息
    ResultT<Integer> updateSkuStatus(Integer skuId, byte shelfStatus,int userId);

    // 删除SKU的信息
    ResultT<Integer> deleteSkuInfo(Integer skuId, int userId);

    // 更新商品SKU的信息
    ResultT<Integer> updateSkuInfo(ProductSkuDto productSkuDto);

    // 获取商品的SKU信息
    ResultT<ResultList<ProductSkuResponse>> getSkuList(SkuListQuery query);

    //按站点获取sku列表
    ResultT<List<ProductSkuResponse>> getSkuListBySiteCode(String siteCode) ;

    // 管理后台：根据SKU-ID获取SKU属性组合及已选属性组
    ResultT<SelSkuAttrGroupResponse> getSelSkuAttrGroup(Integer productId, Integer skuId);

    // 添加SKU：根据SKU-ID获取SKU属性组合及默认属性组
    ResultT<GetSkuAttrGroupResponse> getDefaultSkuAttrGroup(Integer productId);

    // 在SKU页面中编辑SPU
    ResultT<Integer> editSpuAttrOnSkuPage(SpuInfoOnSkuRequest spuInfoOnSkuRequest, Integer userId);

    // 获取预约状态的sku信息
    List<ProductSkuDto> getSkuInfoBySaleStatus(Byte saleStatus);

    // 编辑sku对应的配件信息
    ResultT<Integer> editSkuFitting(Integer skuId, List<Integer> fittingIdList);

    // 检查对应站点下是否已配置了SKU的用户自定义Uri
    ResultT<Boolean> isUserDefinedUriExist(String siteCode, String userDefinedUri);


    /**
     * 按条形码查找SKU
     *
     * @param erpCode
     * @return
     */
    ResultT<ProductSkuDto> getByEANCode(String erpCode);
}
