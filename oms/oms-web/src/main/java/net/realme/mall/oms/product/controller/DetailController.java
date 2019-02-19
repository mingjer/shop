package net.realme.mall.oms.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.cms.facade.CmsPageService;
import net.realme.mall.oms.cms.facade.ProductDetailPageService;
import net.realme.mall.oms.cms.ftl.ProductDetailPageEngine;
import net.realme.mall.oms.consts.PageConst;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.util.PathManager;
import net.realme.mall.product.domain.ProductAttributesViewDto;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import net.realme.mall.product.facade.ProductDetailService;
import net.realme.mall.product.facade.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.nio.channels.OverlappingFileLockException;

/**
 * Created by 91000156 on 2018/9/5 11:01
 */
@Api(tags = {"商详管理"})
@Validated
@RestController
@RequestMapping("/product")
public class DetailController extends BaseController {

    @Autowired
    private CmsPageService cmsPageService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductDetailPageEngine productDetailPageEngine;

    @Autowired
    private ProductDetailPageService productDetailPageService;

    @Autowired
    private PathManager pathManager;

    @Autowired
    private ProductSkuService productSkuService;

    @ApiOperation(value = "FreeMarker变量定义")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "skuId", value = "商品SKU-ID"),
            @ApiImplicitParam(paramType = "query", name = "productId", value = "商品SPU-ID"),
            @ApiImplicitParam(paramType = "query", name = "productName", value = "商品SPU名称"),
            @ApiImplicitParam(paramType = "query", name = "skuName", value = "商品Sku名称"),
            @ApiImplicitParam(paramType = "query", name = "description", value = "商品描述"),
            @ApiImplicitParam(paramType = "query", name = "shortDesc", value = "商品短描述"),
            @ApiImplicitParam(paramType = "query", name = "price", value = "商品价格"),
            @ApiImplicitParam(paramType = "query", name = "colorAttrList", value = "颜色属性列表"),
            @ApiImplicitParam(paramType = "query", name = "specAttrList", value = "配置属性列表"),
            @ApiImplicitParam(paramType = "query", name = "maxQuantity", value = "最大销售数量"),
            @ApiImplicitParam(paramType = "query", name = "overviewUri", value = "橱窗图json"),
            @ApiImplicitParam(paramType = "query", name = "userDefinedUrl", value = "自定义Url，跳转到商品站的Url"),
            @ApiImplicitParam(paramType = "query", name = "saleStatus", value = "销售状态 0 不开卖 1 开卖 2 预约"),
            @ApiImplicitParam(paramType = "query", name = "timeZone", value = "销售预约时间的时区"),
            @ApiImplicitParam(paramType = "query", name = "saleStart", value = "开卖时间'"),
            @ApiImplicitParam(paramType = "query", name = "saleEnd", value = "停止销售时间"),
            @ApiImplicitParam(paramType = "query", name = "reserveStart", value = "预约开始时间"),
            @ApiImplicitParam(paramType = "query", name = "reserveEnd", value = "预约结束时间"),
            @ApiImplicitParam(paramType = "query", name = "countdownWithin", value = "剩余分钟数开始倒计时"),
    })
    @GetMapping("/api_free_marker")
    public void productDetailApi() {

    }

    @ApiOperation(value = "预览商详页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId", value = "商详页面的ID", required = true),
            @ApiImplicitParam(paramType = "query", name = "skuId", value = "商品SKU-ID", required = true),
    })
    @GetMapping("/preview")
    public Object getOneSkuInfo(@Min(1) Integer pageId, @Min(1) Integer skuId) {
        try {
            // 1.根据pageId获取Page构建信息
            CmsPageDto cmsPageDto = cmsPageService.get(pageId);
            // 2.根据skuId获取商详页面的变量信息
            ProductDetailResponse detailResponse = productDetailService.getDetailPageInfo(skuId);
            // 计算关联的sku页面的uri
            updateUriList(detailResponse,detailResponse.getSiteCode());
            // 3.商详页面涉及变量与页面的需要显示的值对应
            ModelMap root = pageVariableSetting(detailResponse);
            // 4.渲染template页面，将相关信息填充到页面中
            String html = productDetailPageEngine.detailPageRender(cmsPageDto, detailResponse.getSiteCode(),root);
            // 5.返回渲染完成后的html页面
            return html;
        } catch (Exception e) {
            logger.error("preview page throw error ",e);
            return errI18N(PageConst.RetCode.PREVIEW_ERR, PageConst.RetCode.PREVIEW_ERR_MSG);
        }
    }

    @ApiOperation(value = "发布商详页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId", value = "商详页面的ID", required = true),
            @ApiImplicitParam(paramType = "query", name = "skuId", value = "商品SKU-ID", required = true),
    })
    @PostMapping("/deploy")
    public Result createSkuInfo(@Min(1) Integer pageId, @Min(1) Integer skuId) {
        logger.info("=== deploy sku detail page page_id [{}] skuId [{}]", pageId, skuId);
        try {
            // 1.根据pageId获取Page构建信息
            CmsPageDto cmsPageDto = cmsPageService.get(pageId);
            // 2.根据skuId获取商详页面的变量信息
            ProductDetailResponse detailResponse = productDetailService.getDetailPageInfo(skuId);
            // 发布版本
            long version = System.currentTimeMillis();
            // 一个sku只有一个site-code
            productDetailPageService.releaseStart(skuId, pageId,detailResponse.getSiteCode() ,version);
            String html = "";
            // 判断上下架状态
            if (detailResponse.getShelfStatus() == PageConst.ShelfStatus.DOWN) {
                // 重写sku页面为下架页面
                html = productDetailPageEngine.overWriteSkuPageContent(detailResponse.getSiteCode(), detailResponse.getUserDefinedUrl());
            } else {
                // 计算关联的sku页面的uri
                updateUriList(detailResponse, detailResponse.getSiteCode());
                // 3.商详页面涉及变量与页面的需要显示的值对应
                ModelMap root = pageVariableSetting(detailResponse);
                // 部署到服务器上
                html = productDetailPageEngine.detailPageDeploy(cmsPageDto, detailResponse, root);
            }
            // 发布结束后更新数据库状态
            productDetailPageService.releaseEnd(skuId, version, html);
            logger.info("=== deploy sku detail page success");
            return ok();
        } catch (OverlappingFileLockException e) {
            logger.error("page release failed. ", e.getMessage());
            return errI18N(PageConst.RetCode.DEPLOY_ERR, PageConst.RetCode.DEPLOY_ERR_MSG);
        } catch (Exception e) {
            logger.error("page release failed. ", e.getMessage());
            return errI18N(PageConst.RetCode.DEPLOY_EXCEPTION, PageConst.RetCode.DEPLOY_EXCEPTION_MSG);
        }
    }

    public ModelMap pageVariableSetting(ProductDetailResponse detailResponse) {
        ModelMap root = new ModelMap();
        String siteCode = detailResponse.getSiteCode();
        // 公共变量赋值
        root.addAttribute("siteCode", siteCode);
        root.addAttribute("siteUrlPrefix", pathManager.getSitePrefix(siteCode));
        root.addAttribute("cdnUrlPrefix", pathManager.getPageCdnPrefix());
        // sku页面要素赋值
        root.addAttribute("data",detailResponse);
        return root;
    }

    /**
     * 将url地址放在SkuAttrJoinGroupDto里面
     *
     * @param detailResponse
     * @param siteCode
     */
    public void updateUriList(ProductDetailResponse detailResponse, String siteCode) {
        logger.info("update sku_id [{}] colorList page uri", detailResponse.getSkuId());
        // 更新ColorList中的Uri
        for (ProductAttributesViewDto colorJumpUriDto : detailResponse.getColorList()) {
            String newUri = pathManager.getShoppingSiteUrlPrefix(siteCode) + "/" + colorJumpUriDto.getUserDefinedUrl();
            newUri = productDetailPageEngine.convertUri(newUri);
            colorJumpUriDto.setUserDefinedUrl(newUri);
            logger.info("color id [{}] and uri is [{}]", colorJumpUriDto.getAttrValId(), newUri);
        }
        logger.info("update sku_id [{}] specList page uri", detailResponse.getSkuId());
        // 更新SpecList中的Uri
        for (ProductAttributesViewDto specJumpUriDto : detailResponse.getSpecList()) {
            String newUri = pathManager.getShoppingSiteUrlPrefix(siteCode) + "/" + specJumpUriDto.getUserDefinedUrl();
            newUri = productDetailPageEngine.convertUri(newUri);
            specJumpUriDto.setUserDefinedUrl(newUri);
            logger.info("spec id [{}] and uri is [{}]", specJumpUriDto.getAttrValId(), newUri);
        }
    }
}
