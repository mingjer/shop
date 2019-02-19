package net.realme.mall.oms.basics.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.realme.framework.util.constant.PatternConst;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultCode;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.exception.BusinessException;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.facade.ServiceSiteService;
import net.realme.mall.oms.basics.req.ServiceSiteCancelReq;
import net.realme.mall.oms.basics.req.ServiceSiteReq;
import net.realme.mall.oms.basics.req.ServiceSiteReviewReq;
import net.realme.mall.oms.config.security.AuthUserDetails;
import net.realme.mall.oms.consts.ServiceSiteConst.AssessStatus;
import net.realme.mall.oms.consts.ServiceSiteConst.AssessType;
import net.realme.mall.oms.consts.ServiceSiteConst.OwnerType;
import net.realme.mall.oms.consts.ServiceSiteConst.SiteStatus;
import net.realme.mall.oms.consts.ServiceSiteConst.SiteType;
import net.realme.mall.oms.consts.ServiceSiteErrorCode;

@Api(tags = { "服务网点管理" })
@Validated
@RestController
@RequestMapping("/service/site")
public class ServiceSiteController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceSiteController.class);

	@Autowired
	private ServiceSiteService serviceSiteService;

	private void checkParam(String pattern, String param, String message) {
		boolean isMatch = java.util.regex.Pattern.matches(pattern, param);
		if (!isMatch) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message);
		}
	}

	private Integer getCurrentUserId() {
		Integer userId = AuthUserDetails.getCurrent().getId();
		return userId;
	}

	@ApiOperation(value = "服务网点批量导入")
	@PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
	public Result upload(@ApiParam(value = "上传的文件", required = true) MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!"xlsx".equals(extension)) {
			logger.error("not support file type={}", extension);
			return err("not support file type");
		}
		try {
			Integer userId = this.getCurrentUserId();
			List<ServiceSiteDto> list = this.getServiceSiteDtoListFromExcel(file.getInputStream(), userId);
			if (list.size() > 0) {
				serviceSiteService.batchAddServiceSite(list);
			}
			return ok();

		} catch (IOException e) {
			logger.error("service site upload error,{}", e);
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_UPLOAD_FAIL.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_UPLOAD_FAIL.getMsg());
		}
	}

	private List<ServiceSiteDto> getServiceSiteDtoListFromExcel(InputStream inputStream, Integer userId)
			throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<ServiceSiteDto> list = new ArrayList<ServiceSiteDto>();
		// 循环行Row
		for (int rowNum = 2, lastNum = sheet.getLastRowNum(); rowNum <= lastNum; rowNum++) {
			ServiceSiteDto serviceSiteDto = this.getServiceSiteDtoFromXssRow(sheet.getRow(rowNum), userId);
			list.add(serviceSiteDto);
		}
		return list;
	}

	private ServiceSiteDto getServiceSiteDtoFromXssRow(XSSFRow row, Integer userId) {
		String countryName = getStringValueFromCell(row.getCell(0));
		String provinceName = getStringValueFromCell(row.getCell(1));
		String cityName = getStringValueFromCell(row.getCell(2));
		String name = getStringValueFromCell(row.getCell(3));
		String address = getStringValueFromCell(row.getCell(4));
		String phoneNumber = getStringValueFromCell(row.getCell(5));
		String openTimeWeekly = getStringValueFromCell(row.getCell(6));
		String openTime = getStringValueFromCell(row.getCell(7));
		String closeTime = getStringValueFromCell(row.getCell(8));
		String postCode = getStringValueFromCell(row.getCell(9));
		String latitude = getStringValueFromCell(row.getCell(10)); // 纬度
		String longitude = getStringValueFromCell(row.getCell(11)); // 经度
		String siteType = getStringValueFromCell(row.getCell(12));
		String ownerType = getStringValueFromCell(row.getCell(13));

		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		serviceSiteDto.setCountryName(countryName);
		serviceSiteDto.setProvinceName(provinceName);
		serviceSiteDto.setCityName(cityName);
		serviceSiteDto.setName(name);
		serviceSiteDto.setAddress(address);
		serviceSiteDto.setPostCode(postCode);
		serviceSiteDto.setPhoneNumber(phoneNumber);
		serviceSiteDto.setOpenTimeWeek(openTimeWeekly);
		serviceSiteDto.setOpenTime(openTime);
		serviceSiteDto.setCloseTime(closeTime);
		serviceSiteDto.setLatitude(latitude);
		serviceSiteDto.setLongitude(longitude);
		serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.DISABLE.getValue())); // 禁用状态，批量导入，需要审核
		String type = (SiteType.EXCLUSIVE.getValue().equals(siteType) ? SiteType.EXCLUSIVE.getValue()
				: (SiteType.AUTHORIZED.getValue().equals(siteType)) ? SiteType.AUTHORIZED.getValue()
						: SiteType.RECEIVE.getValue());
		serviceSiteDto.setType(Byte.valueOf(type));
		String owner = (OwnerType.OPPO.getValue().equals(ownerType) ? OwnerType.OPPO.getValue()
				: OwnerType.REALME.getValue());
		serviceSiteDto.setOwnerType(Byte.valueOf(owner));
		serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.SAVE.getValue())); // 审核状态，已经保存
		serviceSiteDto.setAssessType(Byte.valueOf(AssessType.NEW.getValue())); // 审核类型，新增
		serviceSiteDto.setCreatedBy(userId);
		Long time = System.currentTimeMillis();
		serviceSiteDto.setCreatedAt(time);
		serviceSiteDto.setUpdatedAt(time);
		return serviceSiteDto;
	}

	private String getStringValueFromCell(XSSFCell cell) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat decimalFormat = new DecimalFormat("#.#####");
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			cellValue = cell.getStringCellValue();
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				double d = cell.getNumericCellValue();
				Date date = DateUtil.getJavaDate(d);
				cellValue = sFormat.format(date);
			} else {
				cellValue = decimalFormat.format((cell.getNumericCellValue()));
			}
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
			cellValue = "";
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
			cellValue = "";
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			cellValue = cell.getCellFormula().toString();
		}
		return cellValue;
	}

	@ApiOperation(value = "服务网点列表查询", notes = "服务网点列表查询")
	@GetMapping("/list")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "服务网点代码", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "assessStatus", value = "审核状态", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "type", value = "网点类型", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "countryName", value = "国家", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "provinceName", value = "省份", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "page", value = "分页数", paramType = "query", dataType = "String", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页大小", paramType = "query", dataType = "String", defaultValue = "20"), })
	public Result list(String assessStatus, String type, String id, String countryName, String provinceName,
			@RequestParam(value = "page", defaultValue = "1") String page,
			@RequestParam(value = "limit", defaultValue = "20") String limit) {
		this.checkParam("^\\d{1,10}$", page, "page wrong format");
		this.checkParam("^\\d{1,10}$", limit, "limit wrong format");
		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		if (StringUtils.isNotBlank(assessStatus)) {
			this.checkParam("^[0-3]*$", assessStatus, "assess status wrong format");
			serviceSiteDto.setAssessStatus(Byte.valueOf(assessStatus));
		}
		if (StringUtils.isNotBlank(type)) {
			this.checkParam("^[1-3]*$", type, "type wrong format");
			serviceSiteDto.setType(Byte.valueOf(type));
		}
		if (StringUtils.isNotBlank(id)) {
			this.checkParam("^\\d{1,10}$", id, "id wrong format");
			serviceSiteDto.setId(Integer.parseInt(id));
		}
		serviceSiteDto.setCountryName(countryName);
		serviceSiteDto.setProvinceName(provinceName);
		ResultT<ResultList<ServiceSiteDto>> list = serviceSiteService.list(serviceSiteDto, Integer.parseInt(page),
				Integer.parseInt(limit));
		return ok(list.getData());
	}

	@ApiOperation(value = "保存服务网点", notes = "保存服务网点信息，操作员有审核权限，直接审核通过，否则需要提交审核")
	@PostMapping("/save")
	public Result save(
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody ServiceSiteReq siteReq) {
		Integer userId = this.getCurrentUserId();
		boolean hasAssess = this.checkUserAssess(userId);
		this.checkSaveServiceSiteReq(siteReq);

		if (StringUtils.isNotBlank(siteReq.getId())) {
			ResultT<ServiceSiteDto> siteResult = serviceSiteService
					.getServiceSiteById(Integer.parseInt(siteReq.getId()));
			if (!siteResult.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
			}
			ServiceSiteDto dbServiceSiteDto = siteResult.getData();
			// 待审核网点，不再允许编辑
			if (dbServiceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getMsg());
			}
			if (hasAssess) {
				this.checkServiceSiteReq(siteReq);
				dbServiceSiteDto = this.updateServiceSiteDto(dbServiceSiteDto, siteReq, userId);
				dbServiceSiteDto.setAssessData(null);
				dbServiceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.PASS.getValue()));
			} else {
				// 新增未审核或者未通过网点，直接修改；其它情况，转换成已保存状态
				if (dbServiceSiteDto.getAssessType().equals(Byte.valueOf(AssessType.NEW.getValue()))
						&& dbServiceSiteDto.getStatus().equals(Byte.valueOf(SiteStatus.DISABLE.getValue()))) {
					dbServiceSiteDto = this.updateServiceSiteDto(dbServiceSiteDto, siteReq, userId);
				} else {
					String accessData = JSONObject.toJSONString(siteReq);
					dbServiceSiteDto.setAssessData(accessData);
					dbServiceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.SAVE.getValue()));
				}
			}
			dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
			dbServiceSiteDto.setUpdatedBy(userId);
			ResultT<Integer> result = serviceSiteService.updateServiceSite(dbServiceSiteDto);
			if (!result.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_SAVE_FAIL.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_SAVE_FAIL.getMsg());
			}
			return ok(result.getData());
		}

		ServiceSiteDto serviceSiteDto = this.addServiceSiteDto(siteReq, userId);
		serviceSiteDto.setAssessType(Byte.valueOf(AssessType.NEW.getValue()));// 审核类型-新增
		serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.SAVE.getValue())); // 审核状态-已保存
		serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.DISABLE.getValue())); // 禁用状态
		if (hasAssess) {
			this.checkServiceSiteReq(siteReq);
			serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.PASS.getValue())); // 审核通过
			serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.NORMAL.getValue())); // 启用状态
		}
		ResultT<Integer> result = serviceSiteService.addServiceSite(serviceSiteDto);
		if (!result.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_SAVE_FAIL.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_SAVE_FAIL.getMsg());
		}
		return ok(result.getData());
	}

	/** 校验网点id数据格式 */
	private void checkServiceSiteId(String siteId) {
		if (StringUtils.isBlank(siteId)) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "service site id is required");
		}
		this.checkParam("^\\d{1,10}$", siteId, "service id wrong format");
	}

	/** 校验网点数据且非空 */
	private void checkServiceSiteReq(ServiceSiteReq siteReq) {
		if (StringUtils.isBlank(siteReq.getName())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "service name is required");
		}
		if (StringUtils.isBlank(siteReq.getOwnerType())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "owner type is required");
		}
		this.checkParam("^[1-2]*$", siteReq.getOwnerType(), "owner type wrong format");
		if (StringUtils.isBlank(siteReq.getType())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "service site type is required");
		}
		this.checkParam("^[1-3]*$", siteReq.getOwnerType(), "service site type wrong format");
		if (StringUtils.isBlank(siteReq.getPostCode())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "post code is required");
		}
		if (StringUtils.isBlank(siteReq.getCountryId())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "country id is required");
		}
		if (StringUtils.isBlank(siteReq.getCountryName())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "country name is required");
		}
		if (StringUtils.isBlank(siteReq.getProvinceId())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "province id is required");
		}
		if (StringUtils.isBlank(siteReq.getProvinceName())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "province name is required");
		}
		if (StringUtils.isBlank(siteReq.getCityId())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "city id is required");
		}
		if (StringUtils.isBlank(siteReq.getCityName())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "city name is required");
		}
		if (StringUtils.isBlank(siteReq.getAddress())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "address is required");
		}
		if (StringUtils.isBlank(siteReq.getPhoneNumber())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "phone number is required");
		}
		if (StringUtils.isBlank(siteReq.getLatitude())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "latitude is required");
		}
		this.checkParam(PatternConst.LATITUDE, siteReq.getLatitude(), "latitude wrong format");
		if (StringUtils.isBlank(siteReq.getLongitude())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "longitude is required");
		}
		this.checkParam(PatternConst.LONGITUDE, siteReq.getLongitude(), "longitude wrong format");
		if (StringUtils.isBlank(siteReq.getOpenTimeWeek())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "open time week is required");
		}
		if (StringUtils.isBlank(siteReq.getOpenTime())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "open time is required");
		}
		if (StringUtils.isBlank(siteReq.getCloseTime())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "close time is required");
		}
	}

	/** 校验网点数据且可为空 */
	private void checkSaveServiceSiteReq(ServiceSiteReq siteReq) {
		if (StringUtils.isNotBlank(siteReq.getId())) {
			this.checkParam("^\\d{1,10}$", siteReq.getId(), "service id wrong format");
		}
		if (StringUtils.isBlank(siteReq.getOwnerType())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "owner type is required");
		}
		this.checkParam("^[1-2]*$", siteReq.getOwnerType(), "owner type wrong format");
		if (StringUtils.isBlank(siteReq.getType())) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "service site type is required");
		}
		this.checkParam("^[1-3]*$", siteReq.getType(), "service site type wrong format");
		if (StringUtils.isNotBlank(siteReq.getLatitude())) {
			this.checkParam(PatternConst.LATITUDE, siteReq.getLatitude(), "latitude wrong format");
		}
		if (StringUtils.isNotBlank(siteReq.getLongitude())) {
			this.checkParam(PatternConst.LONGITUDE, siteReq.getLongitude(), "longitude wrong format");
		}
	}

	private ServiceSiteDto addServiceSiteDto(ServiceSiteReq siteReq, Integer userId) {
		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		serviceSiteDto.setName(siteReq.getName());
		serviceSiteDto.setCountryId(siteReq.getCountryId());
		serviceSiteDto.setCountryName(siteReq.getCountryName());
		serviceSiteDto.setProvinceId(siteReq.getProvinceId());
		serviceSiteDto.setProvinceName(siteReq.getProvinceName());
		serviceSiteDto.setCityId(siteReq.getCityId());
		serviceSiteDto.setCityName(siteReq.getCityName());
		serviceSiteDto.setAddress(siteReq.getAddress());
		serviceSiteDto.setLatitude(siteReq.getLatitude());
		serviceSiteDto.setLongitude(siteReq.getLongitude());
		serviceSiteDto.setPostCode(siteReq.getPostCode());
		serviceSiteDto.setPhoneNumber(siteReq.getPhoneNumber());
		serviceSiteDto.setOpenTimeWeek(siteReq.getOpenTimeWeek());
		serviceSiteDto.setOpenTime(siteReq.getOpenTime());
		serviceSiteDto.setCloseTime(siteReq.getCloseTime());
		if (StringUtils.isNotBlank(siteReq.getOwnerType())) {
			serviceSiteDto.setOwnerType(Byte.valueOf(siteReq.getOwnerType()));
		}
		if (StringUtils.isNotBlank(siteReq.getType())) {
			serviceSiteDto.setType(Byte.valueOf(siteReq.getType()));
		}
		serviceSiteDto.setRemark(siteReq.getRemark());
		Long time = System.currentTimeMillis();
		serviceSiteDto.setCreatedAt(time);
		serviceSiteDto.setUpdatedAt(time);
		serviceSiteDto.setCreatedBy(userId);
		serviceSiteDto.setUpdatedBy(userId);
		return serviceSiteDto;
	}

	private ServiceSiteDto updateServiceSiteDto(ServiceSiteDto dbServiceSiteDto, ServiceSiteReq siteReq,
			Integer userId) {
		dbServiceSiteDto.setName(siteReq.getName());
		if (StringUtils.isNotBlank(siteReq.getOwnerType())) {
			dbServiceSiteDto.setOwnerType(Byte.valueOf(siteReq.getOwnerType()));
		}
		if (StringUtils.isNotBlank(siteReq.getType())) {
			dbServiceSiteDto.setType(Byte.valueOf(siteReq.getType()));
		}
		dbServiceSiteDto.setCountryId(siteReq.getCountryId());
		dbServiceSiteDto.setCountryName(siteReq.getCountryName());
		dbServiceSiteDto.setProvinceId(siteReq.getProvinceId());
		dbServiceSiteDto.setProvinceName(siteReq.getProvinceName());
		dbServiceSiteDto.setCityId(siteReq.getCityId());
		dbServiceSiteDto.setCityName(siteReq.getCityName());
		dbServiceSiteDto.setLatitude(siteReq.getLatitude());
		dbServiceSiteDto.setLongitude(siteReq.getLongitude());
		dbServiceSiteDto.setPostCode(siteReq.getPostCode());
		dbServiceSiteDto.setAddress(siteReq.getAddress());
		dbServiceSiteDto.setOpenTimeWeek(siteReq.getOpenTimeWeek());
		dbServiceSiteDto.setOpenTime(siteReq.getOpenTime());
		dbServiceSiteDto.setCloseTime(siteReq.getCloseTime());
		dbServiceSiteDto.setPhoneNumber(siteReq.getPhoneNumber());
		dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
		dbServiceSiteDto.setUpdatedBy(userId);
		return dbServiceSiteDto;
	}

	/**
	 * 校验用户审核权利
	 */
	private boolean checkUserAssess(Integer userId) {

		return false;
	}

	@ApiOperation(value = "服务网点详情查询", notes = "服务网点详情查询")
	@GetMapping("/detail")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "服务网点id", paramType = "query", dataType = "String", required = true, example = "72451") })
	public Result detail(String id) {
		this.checkServiceSiteId(id);
		ResultT<ServiceSiteDto> result = serviceSiteService.getServiceSiteById(Integer.parseInt(id));
		if (!result.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
		}
		ServiceSiteDto serviceSiteDto = result.getData();
		if (StringUtils.isNotBlank(serviceSiteDto.getAssessData())) {
			ServiceSiteDto assessDto = JSONObject.parseObject(serviceSiteDto.getAssessData(), ServiceSiteDto.class);
			serviceSiteDto.setCountryId(assessDto.getCountryId());
			serviceSiteDto.setCountryName(assessDto.getCountryName());
			serviceSiteDto.setProvinceId(assessDto.getProvinceId());
			serviceSiteDto.setProvinceName(assessDto.getProvinceName());
			serviceSiteDto.setCityId(assessDto.getCityId());
			serviceSiteDto.setCityName(assessDto.getCityName());
			serviceSiteDto.setAddress(assessDto.getAddress());
			serviceSiteDto.setLongitude(assessDto.getLongitude());
			serviceSiteDto.setLatitude(assessDto.getLatitude());
			serviceSiteDto.setPostCode(assessDto.getPostCode());
			serviceSiteDto.setOpenTimeWeek(assessDto.getOpenTimeWeek());
			serviceSiteDto.setOpenTime(assessDto.getOpenTime());
			serviceSiteDto.setCloseTime(assessDto.getCloseTime());
			serviceSiteDto.setPhoneNumber(assessDto.getPhoneNumber());
			serviceSiteDto.setOwnerType(assessDto.getOwnerType());
			serviceSiteDto.setType(assessDto.getType());
			serviceSiteDto.setAssessData(null);
		}
		return ok(serviceSiteDto);
	}

	@ApiOperation(value = "变更服务网点信息申请", notes = "变更服务网点申请（新增，撤销，信息修改），操作员有审核权限，直接通过，否则需要审核")
	@PostMapping("/apply")
	public Result apply(
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody ServiceSiteReq siteReq) {
		Integer userId = this.getCurrentUserId();
		boolean hasAssess = this.checkUserAssess(userId);

		// 撤销，信息变更
		if (siteReq.getAssessType().equals(AssessType.DELETE.getValue())
				|| siteReq.getAssessType().equals(AssessType.UPDATE.getValue())) {
			this.checkServiceSiteId(siteReq.getId());

			ResultT<ServiceSiteDto> siteResult = serviceSiteService
					.getServiceSiteById(Integer.parseInt(siteReq.getId()));
			if (!siteResult.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
			}
			ServiceSiteDto dbServiceSiteDto = siteResult.getData();
			if (dbServiceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getMsg());
			}
			dbServiceSiteDto.setAssessType(Byte.valueOf(siteReq.getAssessType()));

			if (hasAssess) {
				if (siteReq.getAssessType().equals(AssessType.DELETE.getValue())) {
					dbServiceSiteDto.setRemark(siteReq.getRemark());
					dbServiceSiteDto.setStatus(Byte.valueOf(SiteStatus.DISABLE.getValue()));
					dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
					dbServiceSiteDto.setUpdatedBy(userId);
				} else {
					this.checkServiceSiteReq(siteReq);
					dbServiceSiteDto = this.updateServiceSiteDto(dbServiceSiteDto, siteReq, userId);
				}

				dbServiceSiteDto.setAssessData(null);
				dbServiceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.PASS.getValue()));
			} else {
				// 撤销时，直接更改状态，不需要保留审核数据
				if (siteReq.getAssessType().equals(AssessType.DELETE.getValue())) {
					dbServiceSiteDto.setRemark(siteReq.getRemark());
				} else {
					this.checkServiceSiteReq(siteReq);
					String accessData = JSONObject.toJSONString(siteReq);
					dbServiceSiteDto.setAssessData(accessData);
				}

				dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
				dbServiceSiteDto.setUpdatedBy(userId);
				dbServiceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.REVIEW.getValue())); // 待审核
			}

			ResultT<Integer> result = serviceSiteService.updateServiceSite(dbServiceSiteDto);
			if (!result.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getMsg());
			}
			return ok(result.getData());
		}

		this.checkServiceSiteReq(siteReq);

		// 新增网点申请
		if (StringUtils.isNotBlank(siteReq.getId())) {
			this.checkServiceSiteId(siteReq.getId());

			ResultT<ServiceSiteDto> siteResult = serviceSiteService
					.getServiceSiteById(Integer.parseInt(siteReq.getId()));
			if (!siteResult.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
			}
			ServiceSiteDto serviceSiteDto = siteResult.getData();
			// 待审核，审核通过，不在允许发起新增网点申请
			if (serviceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))
					|| serviceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.PASS.getValue()))) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_ADD_APPLY_EXIST.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_ADD_APPLY_EXIST.getMsg());
			}
			serviceSiteDto.setAssessType(Byte.valueOf(siteReq.getAssessType()));

			serviceSiteDto = this.updateServiceSiteDto(serviceSiteDto, siteReq, userId);
			if (hasAssess) {
				serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.PASS.getValue())); // 审核通过
				serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.NORMAL.getValue())); // 启用状态
			} else {
				serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.REVIEW.getValue())); // 待审核
			}

			ResultT<Integer> result = serviceSiteService.updateServiceSite(serviceSiteDto);
			if (!result.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getMsg());
			}
			return ok(result.getData());

		} else {
			ServiceSiteDto serviceSiteDto = this.addServiceSiteDto(siteReq, userId);
			serviceSiteDto.setAssessType(Byte.valueOf(AssessType.NEW.getValue()));// 审核类型-新增
			serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.REVIEW.getValue())); // 审核状态-待审核
			serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.DISABLE.getValue())); // 禁用状态
			if (hasAssess) {
				serviceSiteDto.setAssessStatus(Byte.valueOf(AssessStatus.PASS.getValue())); // 审核通过
				serviceSiteDto.setStatus(Byte.valueOf(SiteStatus.NORMAL.getValue())); // 启用状态
			}
			ResultT<Integer> result = serviceSiteService.addServiceSite(serviceSiteDto);
			if (!result.isSuccess()) {
				throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getCode(),
						ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getMsg());
			}
			return ok(result.getData());
		}
	}

	@ApiOperation(value = "服务网点信息审核", notes = "服务网点信息审核，客服经理操作")
	@PostMapping("/review")
	public Result review(
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody ServiceSiteReviewReq siteReviewReq) {
		Integer userId = this.getCurrentUserId();
		ResultT<ServiceSiteDto> siteResult = serviceSiteService
				.getServiceSiteById(Integer.parseInt(siteReviewReq.getId()));
		if (!siteResult.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
		}
		ServiceSiteDto dbServiceSiteDto = siteResult.getData();
		// 只有待审核才能发起审核操作
		if (!dbServiceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_APPLY.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_NOT_APPLY.getMsg());
		}
		// 校验用户是否有审核权限
		/*
		 * boolean hasAssess = this.checkUserAssess(userId); if (!hasAssess) {
		 * return err("user.has.not.assess"); }
		 */
		dbServiceSiteDto.setAssessStatus(Byte.valueOf(siteReviewReq.getAssessStatus()));
		if (siteReviewReq.getAssessStatus().equals(AssessStatus.PASS.getValue())) { // 审核通过时，处理不同的申请
			if (dbServiceSiteDto.getAssessType().equals(Byte.valueOf(AssessType.NEW.getValue()))) {
				dbServiceSiteDto.setStatus(Byte.valueOf(SiteStatus.NORMAL.getValue()));

			} else if (dbServiceSiteDto.getAssessType().equals(Byte.valueOf(AssessType.DELETE.getValue()))) {
				dbServiceSiteDto.setStatus(Byte.valueOf(SiteStatus.DISABLE.getValue()));
			} else {
				// 处理信息变更申请
				dbServiceSiteDto = this.overrideServiceSiteDtoByAssessData(dbServiceSiteDto,
						dbServiceSiteDto.getAssessData());
				dbServiceSiteDto.setAssessData(null);
			}
		}
		dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
		dbServiceSiteDto.setUpdatedBy(userId);
		ResultT<Integer> result = serviceSiteService.updateServiceSite(dbServiceSiteDto);
		if (!result.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_REVIEW_FAIL.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_REVIEW_FAIL.getMsg());
		}
		return ok(result.getData());
	}

	private ServiceSiteDto overrideServiceSiteDtoByAssessData(ServiceSiteDto dbServiceSiteDto, String assessData) {
		if (StringUtils.isBlank(assessData)) {
			return dbServiceSiteDto;
		}
		ServiceSiteDto assessDto = JSONObject.parseObject(assessData, ServiceSiteDto.class);
		dbServiceSiteDto.setCountryId(assessDto.getCountryId());
		dbServiceSiteDto.setCountryName(assessDto.getCountryName());
		dbServiceSiteDto.setProvinceId(assessDto.getProvinceId());
		dbServiceSiteDto.setProvinceName(assessDto.getProvinceName());
		dbServiceSiteDto.setCityId(assessDto.getCityId());
		dbServiceSiteDto.setCityName(assessDto.getCityName());
		dbServiceSiteDto.setAddress(assessDto.getAddress());
		dbServiceSiteDto.setLongitude(assessDto.getLongitude());
		dbServiceSiteDto.setLatitude(assessDto.getLatitude());
		dbServiceSiteDto.setPostCode(assessDto.getPostCode());
		dbServiceSiteDto.setOpenTimeWeek(assessDto.getOpenTimeWeek());
		dbServiceSiteDto.setOpenTime(assessDto.getOpenTime());
		dbServiceSiteDto.setCloseTime(assessDto.getCloseTime());
		dbServiceSiteDto.setPhoneNumber(assessDto.getPhoneNumber());
		dbServiceSiteDto.setOwnerType(assessDto.getOwnerType());
		dbServiceSiteDto.setType(assessDto.getType());
		return dbServiceSiteDto;
	}

	@ApiOperation(value = "服务网点撤销", notes = "服务网点撤销，客服经理操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "服务网点id", paramType = "body", dataType = "String", required = true) })
	@PostMapping("/cancel")
	public Result delete(@Validated @RequestBody ServiceSiteCancelReq req) {
		Integer userId = this.getCurrentUserId();
		// 校验用户是否有撤销权限
		/*
		 * boolean hasAssess = this.checkUserAssess(userId); if (!hasAssess) {
		 * return err("user.has.not.assess"); }
		 */
		ResultT<ServiceSiteDto> siteResult = serviceSiteService.getServiceSiteById(Integer.parseInt(req.getId()));
		if (!siteResult.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
		}
		ServiceSiteDto dbServiceSiteDto = siteResult.getData();
		// 已上架才能发起下架操作
		if (!dbServiceSiteDto.getStatus().equals(Byte.valueOf(SiteStatus.NORMAL.getValue()))) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_HAS_CANCEL.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_HAS_CANCEL.getMsg());
		}
		// 待审核，不允许直接发起下架操作
		if (dbServiceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_ADD_APPLY_EXIST.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_ADD_APPLY_EXIST.getMsg());
		}
		dbServiceSiteDto.setUpdatedBy(userId);
		dbServiceSiteDto.setUpdatedAt(System.currentTimeMillis());
		dbServiceSiteDto.setStatus(Byte.valueOf(SiteStatus.NORMAL.getValue()));
		dbServiceSiteDto.setAssessType(Byte.valueOf(AssessType.DELETE.getValue()));
		serviceSiteService.updateServiceSite(dbServiceSiteDto);
		return ok();
	}

	@ApiOperation(value = "编辑服务网点信息", notes = "编辑服务网点信息，客服经理操作")
	@PostMapping("/update")
	public Result update(
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody ServiceSiteReq siteReq) {
		Integer userId = this.getCurrentUserId();
		this.checkUserAssess(userId);

		this.checkServiceSiteId(siteReq.getId());
		ResultT<ServiceSiteDto> siteResult = serviceSiteService.getServiceSiteById(Integer.parseInt(siteReq.getId()));
		if (!siteResult.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_NOT_FOUND.getMsg());
		}
		ServiceSiteDto dbServiceSiteDto = siteResult.getData();
		if (dbServiceSiteDto.getAssessStatus().equals(Byte.valueOf(AssessStatus.REVIEW.getValue()))) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_EXIST_APPLIY.getMsg());
		}
		this.checkServiceSiteReq(siteReq);
		dbServiceSiteDto = this.updateServiceSiteDto(dbServiceSiteDto, siteReq, userId);
		ResultT<Integer> result = serviceSiteService.updateServiceSite(dbServiceSiteDto);
		if (!result.isSuccess()) {
			throw new BusinessException(ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getCode(),
					ServiceSiteErrorCode.SERVICE_SITE_APPLY_FAIL.getMsg());
		}
		return ok(result.getData());
	}
}
