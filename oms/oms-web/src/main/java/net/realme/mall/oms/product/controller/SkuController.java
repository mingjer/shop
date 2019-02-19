package net.realme.mall.oms.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.BaseController;
import net.realme.id.facade.IdGenerator;
import net.realme.mall.oms.cms.ftl.ProductDetailPageEngine;
import net.realme.mall.oms.config.security.AuthUserDetails;
import net.realme.mall.oms.consts.ProductConst;
import net.realme.mall.oms.product.req.EditFittingReq;
import net.realme.mall.oms.product.req.SkuCreateReq;
import net.realme.mall.oms.product.req.SkuUpdateReq;
import net.realme.mall.oms.util.StringUtil;
import net.realme.mall.product.domain.SkuListQuery;
import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.domain.request.SpuInfoOnSkuRequest;
import net.realme.mall.product.domain.response.GetSkuAttrGroupResponse;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.domain.response.SelSkuAttrGroupResponse;
import net.realme.mall.product.facade.ProductSkuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * Created by 91000156 on 2018/8/29 10:04
 */
@Api(tags = {"SKU管理"})
@Validated
@RestController
@RequestMapping("/sku")
public class SkuController extends BaseController {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private ProductDetailPageEngine productDetailPageEngine;

    @ApiOperation(value = "新增SKU信息")
    @PostMapping("/create")
    public Result createSkuInfo(@RequestBody SkuCreateReq req) {
        // 输入参数项合法性判断
        if (StringUtil.isNullOrEmpty(req.getSkuName()) ||
                StringUtil.existSpaceHeadOrTail(req.getSubTitle()) || StringUtil.existSpaceHeadOrTail(req.getShortDesc()) ||
                StringUtil.isNullOrEmpty(req.getEanCode()) || req.getPackWeight() == null || req.getPackLength() == null ||
                req.getPackWidth() == null || req.getPackHeight() == null || req.getPrice() == null || StringUtil.isNullOrEmpty(req.getUserDefinedUrl()) ||
                StringUtil.isNullOrEmpty(req.getOverviewUri()) || StringUtil.isNullOrEmpty(req.getPcPackImage()) || StringUtil.isNullOrEmpty(req.getMobilePackImage())) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SKU : create sku info req param [{}]", req.toString());
        ResultT<Integer> createResult = null;
        try {
            // 检查sku自定义uri是否已存在
            ResultT<Boolean> existResult = productSkuService.isUserDefinedUriExist(req.getSiteCode(), req.getUserDefinedUrl());
            if (existResult == null || existResult.getData()) {
                logger.info("sku uri info existed don't re-create sku");
                return errI18N(ProductConst.RetCode.SKU_URI_REPEATED, ProductConst.RetCode.SKU_URI_REPEATED_MSG);
            }
            ProductSkuDto productSkuDto = new ProductSkuDto();
            BeanUtils.copyProperties(req, productSkuDto);
            // 获取对应的操作用户
            AuthUserDetails currentUser = AuthUserDetails.getCurrent();
            productSkuDto.setCreatedBy(currentUser.getId());
            createResult = productSkuService.addSkuInfo(productSkuDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (createResult.isSuccess()) {
            logger.info("=== create success");
            return ok(createResult.getData());
        }
        logger.info("create sku service code [{}] msg [{}]", createResult.getCode(), createResult.getMsg());
        return errI18N(ProductConst.RetCode.SKU_CREATE_FAILED, ProductConst.RetCode.SKU_CREATE_FAILED_MSG);
    }

    @ApiOperation(value = "更新SKU上下架状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "商品Sku ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "shelf_status", value = "上下架状态：0-上架，1-下架", required = true),
    })
    @PostMapping("/status")
    public Result statusSkuInfo(@Min(1) Integer id, @RequestParam("shelf_status") byte shelfStatus) {
        logger.info("=== SKU : update sku shelf status id [{}] shelfStatus [{}]", id, shelfStatus);
        // 获取对应的操作用户
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        ResultT<Integer> statusResult = null;
        try {
            statusResult = productSkuService.updateSkuStatus(id, shelfStatus, currentUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (statusResult.isSuccess()) {
            logger.info("=== update sku shelfStatus success");
            return ok(statusResult.getData());
        }
        logger.info("update sku status service code [{}] msg [{}]", statusResult.getCode(), statusResult.getMsg());
        return errI18N(ProductConst.RetCode.SKU_UPDATE_FAILED, ProductConst.RetCode.SKU_UPDATE_FAILED_MSG);
    }

    @ApiOperation(value = "更新SKU信息")
    @PostMapping("/update")
    public Result update(@RequestBody SkuUpdateReq req) {
        // 输入参数项合法性判断
        if (StringUtil.isNullOrEmpty(req.getSkuName()) ||
                StringUtil.existSpaceHeadOrTail(req.getSubTitle()) || StringUtil.existSpaceHeadOrTail(req.getShortDesc()) ||
                StringUtil.isNullOrEmpty(req.getEanCode()) || req.getPackWeight() == null || req.getPackLength() == null ||
                req.getPackWidth() == null || req.getPackHeight() == null || req.getPrice() == null || StringUtil.isNullOrEmpty(req.getUserDefinedUrl()) ||
                StringUtil.isNullOrEmpty(req.getOverviewUri()) || StringUtil.isNullOrEmpty(req.getPcPackImage()) || StringUtil.isNullOrEmpty(req.getMobilePackImage())) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SKU : update sku info req param [{}]", req.toString());
        ResultT<Integer> updateResult = null;
        try {
            ResultT<ProductSkuResponse> getResult = productSkuService.getSkuInfoById(req.getSkuId());
            if (!getResult.isSuccess()) {
                logger.info("sku info not exist");
                return errI18N(ProductConst.RetCode.SKU_NOT_EXIST, ProductConst.RetCode.SKU_NOT_EXIST_MSG);
            }
            // 如果原有的Uri和更新的Uri不同，则进行校验
            if (!getResult.getData().getUserDefinedUrl().equals(req.getUserDefinedUrl())) {
                // 检查sku自定义uri是否已存在
                ResultT<Boolean> existResult = productSkuService.isUserDefinedUriExist(req.getSiteCode(), req.getUserDefinedUrl());
                if (existResult == null || existResult.getData()) {
                    logger.info("sku uri info existed don't re-create sku uri");
                    return errI18N(ProductConst.RetCode.SKU_URI_REPEATED, ProductConst.RetCode.SKU_URI_REPEATED_MSG);
                }
            }
            ProductSkuDto productSkuDto = new ProductSkuDto();
            BeanUtils.copyProperties(req, productSkuDto);
            updateResult = productSkuService.updateSkuInfo(productSkuDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (updateResult.isSuccess()) {
            logger.info("=== sku info update success");
            return ok(updateResult.getData());
        }
        return errI18N(ProductConst.RetCode.SKU_UPDATE_FAILED, ProductConst.RetCode.SKU_UPDATE_FAILED_MSG);
    }

    @ApiOperation(value = "获取指定SKU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "商品Sku ID", required = true),
    })
    @GetMapping("/get")
    public Result getOneSkuInfo(@Min(1) Integer id) {
        ResultT<ProductSkuResponse> getResult = null;
        try {
            getResult = productSkuService.getSkuInfoById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (!getResult.isSuccess()) {
            logger.info("get sku info service code [{}] msg [{}]", getResult.getCode(), getResult.getMsg());
            return errI18N(ProductConst.RetCode.SKU_NOT_EXIST, ProductConst.RetCode.SKU_NOT_EXIST_MSG);
        }
        ProductSkuResponse skuResponse = getResult.getData();
        return ok(skuResponse);
    }

    @ApiOperation(value = "获取SKU列表信息")
    @GetMapping("/list")
    public Result getSkuList(SkuListQuery skuListQuery) {
        ResultT<ResultList<ProductSkuResponse>> list = null;
        try {
            list = productSkuService.getSkuList(skuListQuery);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (list.isSuccess()) {
            return ok(list);
        }
        logger.info("get sku list service code [{}] msg [{}]", list.getCode(), list.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "删除SKU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "商品Sku ID", required = true),
    })
    @PostMapping("/delete")
    public Result deleteSkuInfo(@Min(1) Integer id) {
        // 获取对应的操作用户
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        logger.info("=== SKU : delete sku info user_id [{}] sku_id [{}]", currentUser.getId(), id);
        ResultT<Integer> updateResult = null;
        try {
            ResultT<ProductSkuResponse> getResult = productSkuService.getSkuInfoById(id);
            if (!getResult.isSuccess()) {
                logger.info("sku info not exist");
                return errI18N(ProductConst.RetCode.SKU_NOT_EXIST, ProductConst.RetCode.SKU_NOT_EXIST_MSG);
            }
            // 删除商详对应的页面
            productDetailPageEngine.overWriteSkuPageContent(getResult.getData().getSiteCode(),getResult.getData().getUserDefinedUrl());
            logger.info("deleted sku detail page");
            // 更新对应的数据
            updateResult = productSkuService.deleteSkuInfo(id, currentUser.getId());
            if (!updateResult.isSuccess()) {
                logger.info("sku delete service code [{}] msg [{}]", updateResult.getCode(), updateResult.getMsg());
                return errI18N(ProductConst.RetCode.SKU_DELETE_FAILED, ProductConst.RetCode.SKU_DELETE_FAILED_MSG);
            }
            logger.info("=== sku info delete success");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        return ok(updateResult.getData());
    }

    @ApiOperation(value = "获取SKU可选属性组")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "商品Spu ID", required = true),
    })
    @GetMapping("/def_attr_group")
    public Result getSelSkuAttrGroup(@Min(1) Integer id) {
        ResultT<GetSkuAttrGroupResponse> defAttrGroup = null;
        try {
            defAttrGroup = productSkuService.getDefaultSkuAttrGroup(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (defAttrGroup.isSuccess()) {
            return ok(defAttrGroup);
        }
        logger.info("get sku def_attr_group service code [{}] msg [{}]", defAttrGroup.getCode(), defAttrGroup.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "获取SKU已选属性组")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "spu_id", value = "商品Spu ID", required = true),
            @ApiImplicitParam(paramType = "query", name = "sku_id", value = "商品Sku ID", required = true),
    })
    @GetMapping("/sel_attr_group")
    public Result getSelSkuAttrGroup(@RequestParam("spu_id") @Min(1) Integer spuId, @RequestParam("sku_id") @Min(1) Integer skuId) {
        ResultT<SelSkuAttrGroupResponse> selAttrGroup = null;
        try {
            selAttrGroup = productSkuService.getSelSkuAttrGroup(spuId, skuId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (selAttrGroup.isSuccess()) {
            return ok(selAttrGroup);
        }
        logger.info("get sku sel_attr_group service code [{}] msg [{}]", selAttrGroup.getCode(), selAttrGroup.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "编辑SPU信息")
    @PostMapping("/spu_edit")
    public Result editSpuInfoOnSkuPage(@RequestBody SpuInfoOnSkuRequest spuInfoOnSkuRequest) {
        logger.info("=== SKU : spu_edit req param [{}]", spuInfoOnSkuRequest.toString());
        // 获取对应的操作用户
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        ResultT<Integer> updateResult = null;
        try {
            updateResult = productSkuService.editSpuAttrOnSkuPage(spuInfoOnSkuRequest, currentUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (updateResult.isSuccess()) {
            logger.info("=== sku spu_edit success");
            return ok(updateResult.getData());
        }
        logger.info("spu_edit service code [{}] msg [{}]", updateResult.getCode(), updateResult.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "编辑配件信息")
    @PostMapping("/fitting_edit")
    public Result editFittingInfo(@RequestBody EditFittingReq editFittingReq) {
        if (editFittingReq == null || editFittingReq.getMainSkuId() == null || editFittingReq.getPartSkuId() == null ||
                editFittingReq.getPartSkuId().isEmpty() || editFittingReq.getPartSkuId().size() > 10) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SKU : fitting_edit req param [{}]", editFittingReq.toString());
        ResultT<Integer> fittingResult = null;
        try {
            fittingResult = productSkuService.editSkuFitting(editFittingReq.getMainSkuId(), editFittingReq.getPartSkuId());
            if (fittingResult.isSuccess()) {
                logger.info("=== sku fitting_edit success");
                return ok(fittingResult.getData());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.EXCEPTION_ERROR, ProductConst.RetCode.EXCEPTION_ERROR_MSG);
        }
        logger.info("fitting_edit service code [{}] msg [{}]", fittingResult.getCode(), fittingResult.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }
}
