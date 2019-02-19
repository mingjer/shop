package net.realme.mall.store.user.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.realme.framework.sso.ColorOSUser;
import net.realme.framework.util.constant.PatternConst;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultCode;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.exception.BusinessException;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.mall.basics.dto.IndiaPinCodeDto;
import net.realme.mall.basics.facade.IndiaPinCodeService;
import net.realme.mall.store.common.consts.AddressConst;
import net.realme.mall.store.common.consts.UserErrorCode;
import net.realme.mall.store.user.req.AddressReq;
import net.realme.mall.store.user.req.DefaultAddressReq;
import net.realme.mall.store.user.req.DeleteAddressReq;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;

@Api(tags = { "用户收货地址相关" })
@Validated
@RestController
public class AddressController extends LocalizeController {

	@Autowired
	private IndiaPinCodeService indiaPinCodeService;

	@Autowired
	private UserAddressService userAddressService;

	@ApiOperation(value = "检查邮编地址（pincode）", notes = "检查用户输入的邮编号是否有效")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true, example = "in"),
			@ApiImplicitParam(name = "pinCode", value = "邮编号码（pinCode）", paramType = "query", dataType = "String", required = true, example = "72451") })
	@GetMapping("user/address/check")
	public Result checkAddress(@PathVariable String siteCode,
			@NotBlank(message = "{pincode.required}") @Pattern(regexp = "^\\d{6}$", message = "{pincode.wrong.format}") String pinCode) {
		ResultT<IndiaPinCodeDto> pinCodeResult = indiaPinCodeService.getByPinCode(pinCode);
		if (!pinCodeResult.isSuccess()) {
			throw new BusinessException(UserErrorCode.PINCODE_CHECK_FAIL.getCode(),
					UserErrorCode.PINCODE_CHECK_FAIL.getMsg());
		}
		IndiaPinCodeDto pinCodeDto = pinCodeResult.getData();
		if (!Byte.valueOf(AddressConst.Prepaid.SUPPORT.getValue()).equals(pinCodeDto.getPrepaid())) {
			throw new BusinessException(UserErrorCode.PINCODE_CHECK_FAIL.getCode(),
					UserErrorCode.PINCODE_CHECK_FAIL.getMsg());
		}
		return ok(pinCodeResult.getData());
	}

	@ApiOperation(value = "查询用户收货地址列表", notes = "根据用户信息，查询该用户所属收货地址列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true, example = "in") })
	@GetMapping("user/address/list")
	public Result userAddressList(@PathVariable String siteCode) {
		ResultT<ResultList<UserAddressDto>> result = userAddressService.listAll(ColorOSUser.getCurrentId());
		return ok(result.getData());
	}

	@ApiOperation(value = "保存用户收货地址", notes = "保存用户收货地址，已有地址则更新，否则新增")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true, example = "in") })
	@PostMapping("user/address/save")
	public Result saveUserAddress(@PathVariable String siteCode,
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody AddressReq addressReq) {

		if ("IN".equals(siteCode.toUpperCase())) {
			this.checkParam("^\\d{6}$", addressReq.getPostCode(), "address.post.code.wrong");
			// 检查pincode
			ResultT<IndiaPinCodeDto> pinCodeResult = indiaPinCodeService.getByPinCode(addressReq.getPostCode());
			if (!pinCodeResult.isSuccess()) {
				throw new BusinessException(UserErrorCode.PINCODE_CHECK_FAIL.getCode(),
						UserErrorCode.PINCODE_CHECK_FAIL.getMsg());
			}
			IndiaPinCodeDto pinCodeDto = pinCodeResult.getData();
			if (!Byte.valueOf(AddressConst.Prepaid.SUPPORT.getValue()).equals(pinCodeDto.getPrepaid())) {
				throw new BusinessException(UserErrorCode.PINCODE_CHECK_FAIL.getCode(),
						UserErrorCode.PINCODE_CHECK_FAIL.getMsg());
			}
			addressReq.setPinCode(addressReq.getPinCode());
			addressReq.setCountyId(pinCodeDto.getCountryId());
			addressReq.setCountyName(pinCodeDto.getCountryName());
			addressReq.setProvinceId(pinCodeDto.getProvinceId());
			addressReq.setProvinceName(pinCodeDto.getProvinceName());
			addressReq.setCityId(pinCodeDto.getCityId());
			addressReq.setCityName(pinCodeDto.getCityName());
		}
		addressReq.setSsoid(ColorOSUser.getCurrentId());
		if (StringUtils.isBlank(addressReq.getId())) {
			UserAddressDto userAddressDto = this.addUserAddress(addressReq, siteCode);

			ResultT<String> result = userAddressService.add(userAddressDto);
			if (!result.isSuccess()) {
				throw new BusinessException(UserErrorCode.ADDRESS_ADD_FAIL.getCode(),
						UserErrorCode.ADDRESS_ADD_FAIL.getMsg());
			}
			return ok(result.getData());
		}
		this.checkParam("^\\d{1,19}$", addressReq.getId(), "address.id.wrong");
		UserAddressDto userAddressDto = this.updateUserAddress(addressReq);
		ResultT<Integer> result = userAddressService.update(userAddressDto);
		if (!result.isSuccess()) {
			throw new BusinessException(UserErrorCode.ADDRESS_UPDATE_FAIL.getCode(),
					UserErrorCode.ADDRESS_UPDATE_FAIL.getMsg());
		}
		return ok(result.getData());
	}

	private void checkParam(String pattern, String param, String message) {
		boolean isMatch = java.util.regex.Pattern.matches(pattern, param);
		if (!isMatch) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message);
		}
	}

	private UserAddressDto addUserAddress(AddressReq addressReq, String siteCode) {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setSiteCode(siteCode);
		userAddressDto.setSsoid(ColorOSUser.getCurrentId());
		userAddressDto.setPinCode(addressReq.getPinCode());
		userAddressDto.setPostCode(addressReq.getPostCode());
		userAddressDto.setFullName(addressReq.getFullName());
		userAddressDto.setCountyId(addressReq.getCountyId());
		userAddressDto.setCountyName(addressReq.getCountyName());
		userAddressDto.setProvinceId(addressReq.getProvinceId());
		userAddressDto.setProvinceName(addressReq.getProvinceName());
		userAddressDto.setCityId(addressReq.getCityId());
		userAddressDto.setCityName(addressReq.getCityName());
		userAddressDto.setPhoneCallingCodes(addressReq.getPhoneCallingCodes());
		userAddressDto.setPhoneNumber(addressReq.getPhoneNumber());
		userAddressDto.setAddress1(addressReq.getAddress1());
		userAddressDto.setAddress2(addressReq.getAddress2());
		if (StringUtils.isNotBlank(addressReq.getLongitude())) {
			this.checkParam(PatternConst.LONGITUDE, addressReq.getLongitude(), "address.longitude.wrong");
			userAddressDto.setLongitude(addressReq.getLongitude());
		}
		if (StringUtils.isNotBlank(addressReq.getLatitude())) {
			this.checkParam(PatternConst.LATITUDE, addressReq.getLatitude(), "address.latitude.wrong");
			userAddressDto.setLatitude(addressReq.getLatitude());
		}
		userAddressDto.setEmail(addressReq.getEmail());
		userAddressDto.setIsDefault(Byte.valueOf(addressReq.getIsDefault()));
		userAddressDto.setStatus(Byte.valueOf(AddressConst.Status.NORMAL.getValue()));
		Long time = System.currentTimeMillis();
		userAddressDto.setCreatedAt(time);
		userAddressDto.setUpdatedAt(time);
		return userAddressDto;
	}

	private UserAddressDto updateUserAddress(AddressReq addressReq) {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setId(Long.parseLong(addressReq.getId()));
		userAddressDto.setSsoid(ColorOSUser.getCurrentId());
		userAddressDto.setPinCode(addressReq.getPinCode());
		userAddressDto.setPostCode(addressReq.getPostCode());
		userAddressDto.setFullName(addressReq.getFullName());
		userAddressDto.setCountyId(addressReq.getCountyId());
		userAddressDto.setCountyName(addressReq.getCountyName());
		userAddressDto.setProvinceId(addressReq.getProvinceId());
		userAddressDto.setProvinceName(addressReq.getProvinceName());
		userAddressDto.setCityId(addressReq.getCityId());
		userAddressDto.setCityName(addressReq.getCityName());
		userAddressDto.setPhoneCallingCodes(addressReq.getPhoneCallingCodes());
		userAddressDto.setPhoneNumber(addressReq.getPhoneNumber());
		userAddressDto.setAddress1(addressReq.getAddress1());
		userAddressDto.setAddress2(addressReq.getAddress2());
		if (StringUtils.isNotBlank(addressReq.getLongitude())) {
			this.checkParam(PatternConst.LONGITUDE, addressReq.getLongitude(), "address.longitude.wrong");
			userAddressDto.setLongitude(addressReq.getLongitude());
		}
		if (StringUtils.isNotBlank(addressReq.getLatitude())) {
			this.checkParam(PatternConst.LATITUDE, addressReq.getLatitude(), "address.latitude.wrong");
			userAddressDto.setLatitude(addressReq.getLatitude());
		}
		userAddressDto.setEmail(addressReq.getEmail());
		userAddressDto.setIsDefault(Byte.valueOf(addressReq.getIsDefault()));
		userAddressDto.setUpdatedAt(System.currentTimeMillis());
		return userAddressDto;
	}

	@ApiOperation(value = "删除用户收货地址", notes = "删除用户收货地址")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true, example = "in") })
	@PostMapping("user/address/delete")
	public Result deleteUserAddress(@PathVariable String siteCode,
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody DeleteAddressReq addressReq) {
		addressReq.setSiteCode(siteCode);
		addressReq.setSsoid(ColorOSUser.getCurrentId());
		ResultT<Integer> result = userAddressService.deleteById(addressReq.getSsoid(),
				Long.parseLong(addressReq.getId()));
		if (!result.isSuccess()) {
			throw new BusinessException(UserErrorCode.ADDRESS_DELETE_FAIL.getCode(),
					UserErrorCode.ADDRESS_DELETE_FAIL.getMsg());
		}
		return ok(result.getData());
	}

	@ApiOperation(value = "设置用户默认收货地址（该接口废弃）", notes = "设置用户默认收货地址，该接口已经废弃")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true, example = "in") })
	@PostMapping("user/address/default")
	public Result setUserDefaultAddress(@PathVariable String siteCode,
			@ApiParam(name = "请求对象", value = "json格式", required = true) @Validated @RequestBody DefaultAddressReq addressReq) {
		addressReq.setSiteCode(siteCode);
		addressReq.setSsoid(ColorOSUser.getCurrentId());
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setId(Long.parseLong(addressReq.getId()));
		userAddressDto.setSsoid(addressReq.getSsoid());
		userAddressDto.setIsDefault(Byte.valueOf(addressReq.getIsDefault()));
		ResultT<Integer> result = userAddressService.setDefault(userAddressDto);
		if (!result.isSuccess()) {
			throw new BusinessException(UserErrorCode.ADDRESS_DEFAULT_FAIL.getCode(),
					UserErrorCode.ADDRESS_DEFAULT_FAIL.getMsg());
		}
		return ok(result.getData());
	}
}
