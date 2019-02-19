package net.realme.mall.product.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.beantool.DetailPageJumpUriConvert;
import net.realme.mall.product.beantool.ProductDetailConvert;
import net.realme.mall.product.beantool.SkuAttrJoinGroupConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductDetailPageMapper;
import net.realme.mall.product.dao.ProductSkuMapper;
import net.realme.mall.product.domain.SkuDetailUrisViewDto;
import net.realme.mall.product.domain.ProductAttributesViewDto;
import net.realme.mall.product.domain.response.ProductDetailFittings;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.domain.response.SkuLiveStatusResponse;
import net.realme.mall.product.facade.ProductDetailService;
import net.realme.mall.product.facade.ProductSkuAttributeService;
import net.realme.mall.product.facade.ProductSkuService;
import net.realme.mall.product.model.SkuDetailUrisView;
import net.realme.mall.product.model.ProductAttributesView;
import net.realme.mall.product.model.ProductSku;
import net.realme.mall.product.model.SkuFittingView;
import net.realme.shared.config.inventory.InventorySharedConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static net.realme.mall.product.common.ProductConst.AttrInfo.COLOR_ATTR_ID_STR;
import static net.realme.mall.product.common.ProductConst.AttrInfo.SPEC_ATTR_ID_STR;

/**
 * Created by 91000156 on 2018/9/5 11:22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private RedisCache<Integer> cacheSkuLogicStock;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailConvert productDetailConvert;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private ProductDetailPageMapper priceSymbolMapper;

    @Autowired
    private ProductDetailPageMapper productDetailPageMapper;

    @Autowired
    private SkuAttrJoinGroupConvert skuAttrJoinGroupConvert;

    @Autowired
    private ProductSkuAttributeService productSkuAttributeService;

    @Autowired
    private DetailPageJumpUriConvert detailPageJumpUriConvert;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ProductDetailResponse getDetailPageInfo(Integer skuId) {
        /** 1·根据skuId查询sku基本信息 **/
        Example example = new Example(ProductSku.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 按照SkuId和状态查询一条SKU信息
        ProductSku productSku = productSkuMapper.selectOneByExample(example);
        // 根据siteCode查询相应国家的货币符号
        String priceSymbol = priceSymbolMapper.getPriceSymbolMapper(productSku.getSiteCode());
        // 返回信息转VO
        ProductDetailResponse productDetailResponse = productDetailConvert.toProductDetailResponse(productSku);
        // 记录货币符号
        productDetailResponse.setPriceSymbol(priceSymbol);
        /** 2·获取sku-id相关的属性 **/
        Map<String,String> skuAttr = productSkuAttributeService.getSkuAttribute(skuId);
        productDetailResponse.setSkuAttrGroup(skuAttr);
        // 获取此商品SPU对应的所有颜色
        List<ProductAttributesView> skuAttrColorList = productDetailPageMapper.getSkuAttrListByType(productSku.getProductId(),ProductDetailPageMapper.colorAttrId);
        // 获取此商品SPU对应的所有配置
        List<ProductAttributesView> skuAttrSpecList = productDetailPageMapper.getSkuAttrListByType(productSku.getProductId(),ProductDetailPageMapper.specAttrId);
        // 转化成有用户自定义URL的值
        List<ProductAttributesViewDto> colorDtoList = skuAttrJoinGroupConvert.toSkuAttrJoinGroupDtoList(skuAttrColorList);
        List<ProductAttributesViewDto> specDtoList = skuAttrJoinGroupConvert.toSkuAttrJoinGroupDtoList(skuAttrSpecList);
        /** 将颜色和配置设置为有序的排列 **/
        detailPageAttrGroup(productDetailResponse, colorDtoList, specDtoList, productSku.getSpecWeights());
        /** 3.1·获取当前SKU对应的商品下需要进行展示的各SKU的URI,每组属性对应的Url添加 **/
        List<SkuDetailUrisView> uriList = productDetailPageMapper.getJumpUriInfo(productSku.getProductId());
        /** 3.2·将UrisView转化为ViewDto **/
        List<SkuDetailUrisViewDto> uriDtoList = detailPageJumpUriConvert.toDetailPageJumpUriDto(uriList);
        /** 3.3·以要显示的SKU信息为基准，为各自添加user_defined_url **/
        addUriResponse(productDetailResponse,uriDtoList);
        /** 4·添加配件信息到Response中 **/
        List<ProductDetailFittings> skuFittingList = getSkuFittings(skuId);
        productDetailResponse.setSkuFittingList(skuFittingList);
        return productDetailResponse;
    }

    @Override
    public SkuLiveStatusResponse getSkuLiveStatusInfo(Integer skuId) {
        // 初始化返回信息
        SkuLiveStatusResponse skuLiveStatusResponse = new SkuLiveStatusResponse();
        // 1.获取当前sku的状态
        ResultT<ProductSkuResponse> skuInfo =  productSkuService.getSkuInfoById(skuId);
        if (skuInfo == null || skuInfo.getData() == null) {
            return skuLiveStatusResponse;
        }
        skuLiveStatusResponse.setShelfStatus(skuInfo.getData().getShelfStatus());
        skuLiveStatusResponse.setSaleStatus(skuInfo.getData().getSaleStatus());
        skuLiveStatusResponse.setServerNowTime(System.currentTimeMillis());
        skuLiveStatusResponse.setCountdownWithin(skuInfo.getData().getCountdownWithin());
        skuLiveStatusResponse.setSaleStartTime(skuInfo.getData().getSaleStart());
        // 库存情况，若没有库存，则前端显示预约
        String stockCacheKey = String.format(InventorySharedConstant.CACHE_SKU_LOGIC_INVENTORY, skuId);
        Integer stock = cacheSkuLogicStock.get(stockCacheKey);
        if (stock == null || stock <= 0) {
            skuLiveStatusResponse.setStockStatus(ProductConst.SkuStockStatus.OUT_STOCK);
        } else {
            skuLiveStatusResponse.setStockStatus(ProductConst.SkuStockStatus.IN_STOCK);
        }
        return skuLiveStatusResponse;
    }

    @Override
    public List<ProductDetailFittings> getSkuFittings(Integer skuId) {
        // 查询此sku关联的配件商品
        List<SkuFittingView> skuFittingViewList = productDetailPageMapper.getDetailFittingByMainSkuId(skuId);
        // 转化成response的形式
        List<ProductDetailFittings> skuDetailFittings = productDetailConvert.toDetailFittingList(skuFittingViewList);
        // 查询获取对应的货币符号
        for(ProductDetailFittings fitting : skuDetailFittings){
            String symbol = productDetailPageMapper.getPriceSymbolMapper(fitting.getSiteCode());
            fitting.setSymbol(symbol);
        }
        return skuDetailFittings;
    }

    /**
     * 将颜色和配置设置为有序的排列
     *
     * @param productDetailResponse
     * @param colorDtoList
     * @param specDtoList
     * @param specWeights
     */
    private void detailPageAttrGroup(ProductDetailResponse productDetailResponse, List<ProductAttributesViewDto> colorDtoList, List<ProductAttributesViewDto> specDtoList, String specWeights) {
        if (StringUtil.isEmpty(specWeights)) {
            productDetailResponse.setColorList(colorDtoList);
            productDetailResponse.setSpecList(specDtoList);
        } else {
            // 已设置权重，则按照权重进行输出
            JSONObject jsonObject = JSON.parseObject(specWeights);
            Object colorArray = jsonObject.get(COLOR_ATTR_ID_STR);
            Object specArray = jsonObject.get(SPEC_ATTR_ID_STR);
            // 转成list
            List colorList = JSONObject.parseArray(colorArray.toString());
            List specList = JSONObject.parseArray(specArray.toString());
            // 按照list中的顺序去填充商详页面的sku属性
            List<ProductAttributesViewDto> retColor = new ArrayList<>();
            List<ProductAttributesViewDto> retSpec = new ArrayList<>();
            for (Object color : colorList) {
                ProductAttributesViewDto model = new ProductAttributesViewDto();
                for (ProductAttributesViewDto oneAttr : colorDtoList) {
                    // 相等则加入到新的List中
                    if (oneAttr.getAttrValId().equals(color.toString())) {
                        retColor.add(oneAttr);
                        break;
                    }
                }
            }
            for (Object spec : specList) {
                ProductAttributesViewDto model = new ProductAttributesViewDto();
                for (ProductAttributesViewDto specAttr : specDtoList) {
                    // 相等则加入到新的List中
                    if (specAttr.getAttrValId().equals(spec.toString())) {
                        retSpec.add(specAttr);
                        break;
                    }
                }
            }
            productDetailResponse.setColorList(retColor);
            productDetailResponse.setSpecList(retSpec);
        }
    }

    /**
     * 将地址添加到SkuAttrJoinGroupDto中
     *
     * @param detailResponse
     * @param skuShowList
     */
    public void addUriResponse(ProductDetailResponse detailResponse, List<SkuDetailUrisViewDto> skuShowList) {
        // 两个Product的属性List-最终要转化为sku中所含有的颜色和属性-包含关系
        List<ProductAttributesViewDto> spuColorList = detailResponse.getColorList();
        List<ProductAttributesViewDto> spuSpecList = detailResponse.getSpecList();
        // 一个sku页面所持有的属性组
        List<ProductAttributesViewDto> skuColorList = new ArrayList<>();
        List<ProductAttributesViewDto> skuSpecList = new ArrayList<>();
        for (ProductAttributesViewDto spuColor : spuColorList) {
            // 循环，给colorGroupDto添加Uri
            for (SkuDetailUrisViewDto skuShowView : skuShowList) {
                // 商品spu对应的颜色和连表结果中的colorId相符则添加对应uri
                if (skuShowView.getColorId().equals(spuColor.getAttrValId())) {
                    spuColor.setUserDefinedUrl(skuShowView.getUserDefinedUrl());
                    // 多个相同颜色的SKU只能插入一次
                    if(!skuColorList.contains(spuColor)){
                        skuColorList.add(spuColor);
                    }
                }
            }
        }
        // 配置是基于颜色进行跳转的,对应的颜色下的配置，要以颜色为基准
        String nowSkuColorId = detailResponse.getSkuAttrGroup().get(COLOR_ATTR_ID_STR);
        for (ProductAttributesViewDto spuSpec : spuSpecList) {
            // 循环，给specGroupDto添加Uri
            for (SkuDetailUrisViewDto skuShowView : skuShowList) {
                // 为当前sku对应的颜色下的所有配置添加Uri,若缺少配置项的sku则不填
                if (skuShowView.getColorId().equals(nowSkuColorId) && skuShowView.getSpecId().equals(spuSpec.getAttrValId())) {
                    spuSpec.setUserDefinedUrl(skuShowView.getUserDefinedUrl());
                    // 多个相同配置的SKU只能插入一次
                    if(!skuSpecList.contains(spuSpec)){
                        skuSpecList.add(spuSpec);
                    }
                }
            }
        }
        // sku持有的属性赋值
        detailResponse.setColorList(skuColorList);
        detailResponse.setSpecList(skuSpecList);
    }
}
