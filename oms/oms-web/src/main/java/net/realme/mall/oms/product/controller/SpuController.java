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
import net.realme.mall.oms.config.security.AuthUserDetails;
import net.realme.mall.oms.consts.ProductConst;
import net.realme.mall.oms.product.req.SpuCreateReq;
import net.realme.mall.oms.product.req.SpuUpdateReq;
import net.realme.mall.oms.util.StringUtil;
import net.realme.mall.product.domain.ProductDto;
import net.realme.mall.product.domain.ProductListQuery;
import net.realme.mall.product.domain.request.ProductGetUpdDto;
import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.facade.ProductSpuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.*;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.product.controller
 *
 * @author 91000044
 * @date 2018/8/23 13:42
 */
@Api(tags = {"SPU管理"})
@Validated
@RestController
@RequestMapping("/spu")
public class SpuController extends BaseController {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ProductSpuService productSpuService;

    @ApiOperation(value = "新增SPU")
    @PostMapping("/create")
    public Result create(@RequestBody SpuCreateReq req) {
        // 输入参数项合法性判断
        if (StringUtil.isNullOrEmpty(req.getName()) || SpuController.validationCreateOptions(req.getOptions())) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SPU : create spu info req param [{}]", req.toString());
        ProductDto productDto = new ProductDto();
        // copy时会将name前后的空格去掉
        BeanUtils.copyProperties(req, productDto);
        productDto.setCreatedTime(System.currentTimeMillis());
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        productDto.setCreatedBy(currentUser.getId());
        ResultT<Integer> createResult = null;
        try {
            createResult = productSpuService.add(productDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (createResult.isSuccess()) {
            logger.info("=== create success");
            return ok(createResult.getData());
        }
        logger.info("create spu service code [{}] msg [{}]",createResult.getCode(),createResult.getMsg());
        return errI18N(ProductConst.RetCode.SPU_CREATE_FAILED, ProductConst.RetCode.SPU_CREATE_FAILED_MSG);
    }

    @ApiOperation(value = "更新SPU基本信息")
    @PostMapping("/update")
    public Result update(@RequestBody SpuUpdateReq req) {
        // 输入参数项合法性判断
        if (req.getProductId() == null || StringUtil.isNullOrEmpty(req.getName()) || SpuController.validationUpdOptions(req.getOptions())) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SPU : update spu info req param [{}]", req.toString());
        ResultT<Integer> updateResult = null;
        try {
            ResultT<ProductGetUpdDto> getResult = productSpuService.get(req.getProductId());
            if (!getResult.isSuccess()) {
                return errI18N(ProductConst.RetCode.SPU_NOT_EXIST, ProductConst.RetCode.SPU_NOT_EXIST_MSG);
            }
            ProductGetUpdDto getUpdDto = getResult.getData();
            BeanUtils.copyProperties(req, getUpdDto);
            updateResult = productSpuService.update(getUpdDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (updateResult.isSuccess()) {
            logger.info("=== spu info update success");
            return ok(updateResult.getData());
        }
        logger.info("update spu service code [{}] msg [{}]",updateResult.getCode(),updateResult.getMsg());
        return errI18N(ProductConst.RetCode.SPU_UPDATE_FAILED, ProductConst.RetCode.SPU_UPDATE_FAILED_MSG);
    }

    @ApiOperation(value = "修改SPU状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "商品ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "shelf_status", value = "上下架状态：0-上架，1-下架", required = true),
    })
    @PostMapping("/status")
    public Result status(@Min(1) Integer id, @RequestParam("shelf_status") byte shelfStatus) {
        // 输入参数项合法性判断
        if (id == null || StringUtil.isNullOrEmpty(String.valueOf(shelfStatus))) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        logger.info("=== SPU : update spu shelf status id [{}] shelfStatus [{}]", id, shelfStatus);
        // 获取对应的操作用户
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        ResultT<Integer> statusResult = null;
        try {
            // 更新上下架状态
            statusResult = productSpuService.status(id, shelfStatus, currentUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (statusResult.isSuccess()) {
            logger.info("=== update spu shelfStatus success");
            return ok(statusResult.getData());
        }
        logger.info("update spu status service code [{}] msg [{}]", statusResult.getCode(), statusResult.getMsg());
        return errI18N(ProductConst.RetCode.SPU_UPDATE_FAILED, ProductConst.RetCode.SPU_UPDATE_FAILED_MSG);
    }

    @ApiOperation(value = "获取SPU信息")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "商品ID", required = true),
    })
    public Result get(@Min(1) Integer id) {
        // 输入参数项合法性判断
        if (id == null) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        ResultT<ProductGetUpdDto> getResult = null;
        try {
            getResult = productSpuService.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (!getResult.isSuccess()) {
            logger.info("get spu info service code [{}] msg [{}]", getResult.getCode(), getResult.getMsg());
            return errI18N(ProductConst.RetCode.SPU_NOT_EXIST, ProductConst.RetCode.SPU_NOT_EXIST_MSG);
        }
        ProductGetUpdDto getUpdDto = getResult.getData();
        return ok(getUpdDto);
    }

    @ApiOperation(value = "SPU列表")
    @GetMapping("/list")
    public Result list(ProductListQuery req) {
        // 输入参数项合法性判断
        if ((StringUtil.isNotEmpty(req.getProductName()) && StringUtil.isSpecialChar(req.getProductName())) ||
                (StringUtil.isNotEmpty(req.getBrandCode()) && StringUtil.isSpecialChar(req.getBrandCode())) ||
                (StringUtil.isNotEmpty(req.getCategoryCode()) && StringUtil.isSpecialChar(req.getCategoryCode()))) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        ResultT<ResultList<ProductDto>> list = null;
        try {
            list = productSpuService.list(req);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (list.isSuccess()) {
            return ok(list);
        }
        logger.info("get spu list service code [{}] msg [{}]", list.getCode(), list.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "删除SPU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "商品Spu ID", required = true),
    })
    @PostMapping("/delete")
    public Result deleteSkuInfo(@Min(1) Integer id) {
        // 输入参数项合法性判断
        if (id == null) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        // 获取对应的操作用户
        AuthUserDetails currentUser = AuthUserDetails.getCurrent();
        ResultT<Integer> updateResult = null;
        logger.info("=== SPU : delete spu info user_id [{}] sku_id [{}]", currentUser.getId(), id);
        try {
            updateResult = productSpuService.deleteSpuInfo(id, currentUser.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        if (updateResult.isSuccess()) {
            logger.info("=== spu info delete success");
            return ok(updateResult.getData());
        }
        logger.info("spu delete service code [{}] msg [{}]", updateResult.getCode(), updateResult.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    @ApiOperation(value = "根据SPU名称获取数量")
    @GetMapping("/count")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = true),
    })
    public Result getCountByName(String name) {
        // 输入参数项合法性判断
        if (StringUtil.isNullOrEmpty(name)) {
            return errI18N(ProductConst.RetCode.VALIDATE_FAILED, ProductConst.RetCode.VALIDATE_FAILED_MSG);
        }
        // 去掉字符串前后的空格在进行查询
        name = name.trim();
        ResultT<Integer> countResult = null;
        try {
            countResult = productSpuService.getSpuCountByName(name);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(ProductConst.RetCode.SERVICE_INVOKE_FAILED, ProductConst.RetCode.SERVICE_INVOKE_FAILED_MSG);
        }
        // 返回查询结果
        if (countResult.isSuccess()) {
            return ok(countResult.getData());
        }
        logger.info("spu get count service code [{}] msg [{}]", countResult.getCode(), countResult.getMsg());
        return errI18N(ProductConst.RetCode.OPERATED_FAILED, ProductConst.RetCode.OPERATED_FAILED_MSG);
    }

    /**
     * 创建时校验spu属性
     *
     * @param options
     * @return
     */
    public static boolean validationCreateOptions(HashMap<Integer, List<String>> options) {
        // 初始化判断结果
        boolean validateFlg = false;
        // 为null直接返回
        if (options == null) {
            return !validateFlg;
        }
        // 遍历每一个属性
        for (Map.Entry<Integer, List<String>> entry : options.entrySet()) {
            // 判断每一个属性组中是否有重复
            if (entry.getValue() == null || entry.getValue().size() != new HashSet<String>(entry.getValue()).size()) {
                // 去重之后不相等，则说明有重复
                validateFlg = true;
                break;
            }
            // 遍历每个属性下的，属性list
            for (String str : entry.getValue()) {
                if (StringUtil.isNullOrEmpty(str) || StringUtil.existSpaceHeadOrTail(str)) {
                    validateFlg = true;
                }
            }
        }
        return validateFlg;
    }

    /**
     * 更新时校验属性的合法性
     *
     * @param options
     * @return
     */
    public static boolean validationUpdOptions(HashMap<Integer, List<SpuAttrUpdate>> options) {
        // 初始化判断结果
        boolean validateFlg = false;
        // 为null直接返回
        if (options == null) {
            return !validateFlg;
        }
        // 遍历每一个属性
        for (Map.Entry<Integer, List<SpuAttrUpdate>> entry : options.entrySet()) {
            // 判断每一个属性组中是否有重复
            if (entry.getValue() != null) {
                List<String> attrList = new ArrayList<>();
                for (SpuAttrUpdate attr : entry.getValue()) {
                    attrList.add(attr.getAttrVal());
                }
                // 没有重复则进行下面的判断
                if (attrList.size() != new HashSet<String>(attrList).size()) {
                    validateFlg = true;
                    break;
                }
            } else {
                validateFlg = true;
                break;
            }
            // 遍历每个属性下的，属性list
            for (SpuAttrUpdate spuAttrUpdate : entry.getValue()) {
                if (StringUtil.isNullOrEmpty(spuAttrUpdate.getAttrVal()) || StringUtil.existSpaceHeadOrTail(spuAttrUpdate.getAttrVal())) {
                    validateFlg = true;
                }
            }
        }
        return validateFlg;
    }
}
