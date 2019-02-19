package net.realme.mall.user.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.rest.client.RestHttpClient;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.common.AddressConst;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;

@Service
public class UserAddressNetServiceImpl implements UserAddressService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String ADDRESS_ADD_URL = "/delivery/add-address";
	private static final String ADDRESS_LIST_URL = "/delivery/list-address";
	private static final String ADDRESS_UPDATE_URL = "/delivery/modify-address";
	private static final String ADDRESS_DELETE_URL = "/delivery/delete-address";
	private static final String ADDRESS_LIST_CACHE = "ADDRESS_LIST_CACHE_";

	@Value("${address.host}")
	private String host;

	@Value("${address.appKey}")
	private String appKey;

	@Value("${address.appSecret}")
	private String appSecret;

	@Autowired
	private RestHttpClient httpClient;

	@Autowired
	private RedisCache<List<UserAddressDto>> redisCache;

	@Override
	public ResultT<String> add(UserAddressDto userAddressDto) {
		Map<String, String> paramMap = this.getRequestParam(userAddressDto, ADDRESS_ADD_URL);
		JSONObject response = this.request(paramMap, ADDRESS_ADD_URL);
		if (response == null || !"true".equals(response.getString("success"))) {
			return ResultT.fail();
		}
		redisCache.delete(ADDRESS_LIST_CACHE + userAddressDto.getSsoid());
		JSONObject address = response.getJSONObject("data");
		return ResultT.success(address.getString("addressId"));
	}

	@Override
	public ResultT<UserAddressDto> getDefault(long ssoid) {
		ResultT<ResultList<UserAddressDto>> result = this.listAll(ssoid);
		if (!result.isSuccess()) {
			return ResultT.fail();
		}
		List<UserAddressDto> list = result.getData().getRecords();
		for (UserAddressDto userAddressDto : list) {
			if (userAddressDto.getIsDefault().equals(Byte.valueOf(AddressConst.DefaultStatus.DEFAULT.getValue()))) {
				return ResultT.success(userAddressDto);
			}
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<UserAddressDto> getById(long ssoid, long userAddressId) {
		ResultT<ResultList<UserAddressDto>> result = this.listAll(ssoid);
		if (!result.isSuccess()) {
			return ResultT.fail();
		}
		List<UserAddressDto> list = result.getData().getRecords();
		for (UserAddressDto userAddressDto : list) {
			if (userAddressDto.getId().equals(userAddressId)) {
				return ResultT.success(userAddressDto);
			}
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<ResultList<UserAddressDto>> listAll(long ssoid) {
		List<UserAddressDto> cacheAddressList = this.getUserAddressDtoListFromCache(ssoid);
		if (cacheAddressList != null && !cacheAddressList.isEmpty()) {
			ResultList<UserAddressDto> result = new ResultList<>();
			result.setRecords(cacheAddressList);
			return ResultT.success(result);
		}

		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setSsoid(ssoid);
		Map<String, String> paramMap = this.getRequestParam(userAddressDto, ADDRESS_LIST_URL);
		JSONObject response = this.request(paramMap, ADDRESS_LIST_URL);
		if (response == null || !"true".equals(response.getString("success"))) {
			return ResultT.fail();
		}
		ResultList<UserAddressDto> result = new ResultList<>();
		List<UserAddressDto> addressList = this.toUserAddressDtoList(response);
		result.setRecords(addressList);

		this.setUserAddressDtoListToCache(ssoid, addressList);
		return ResultT.success(result);
	}

	private List<UserAddressDto> toUserAddressDtoList(JSONObject result) {
		List<UserAddressDto> list = new LinkedList<UserAddressDto>();
		JSONArray addressArray = result.getJSONArray("data");
		if (addressArray == null) {
			return list;
		}
		for (int i = 0, len = addressArray.size(); i < len; i++) {
			UserAddressDto userAddressDto = this.toUserAddressDto(addressArray.getJSONObject(i));
			list.add(userAddressDto);
		}
		return list;
	}

	private UserAddressDto toUserAddressDto(JSONObject result) {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setId(result.getLong("addresssId"));
		userAddressDto.setSsoid(result.getLong("ssoid"));
		if (result.getString("countryCode").equals("IN")) {
			userAddressDto.setPinCode(result.getString("postCode"));
		}
		userAddressDto.setPostCode(result.getString("postCode"));
		userAddressDto.setFullName(result.getString("contact"));
		userAddressDto.setSiteCode(result.getString("countryCode"));
		userAddressDto.setCountyId(result.getString("countryCode"));
		userAddressDto.setCountyName(result.getString("countryName"));
		userAddressDto.setProvinceId(result.getString("provinceId"));
		userAddressDto.setProvinceName(result.getString("provinceName"));
		userAddressDto.setCityId(result.getString("cityId"));
		userAddressDto.setCityName(result.getString("cityName"));
		userAddressDto.setEmail(result.getString("email"));
		userAddressDto.setStatus(Byte.valueOf(AddressConst.Status.EFFECTIVE.getValue()));
		userAddressDto.setAddress1(result.getString("detailAddress"));
		userAddressDto.setAddress2(result.getString("landmark"));
		userAddressDto.setCreatedAt(result.getLong("createTime"));
		userAddressDto.setUpdatedAt(result.getLong("updateTime"));
		String isDefault = "YES".equals(result.getString("isDefault")) ? AddressConst.DefaultStatus.DEFAULT.getValue()
				: AddressConst.DefaultStatus.NORMAL.getValue();
		userAddressDto.setIsDefault(Byte.valueOf(isDefault));
		String[] mobile = result.getString("mobile").split(" ");
		if (mobile.length > 1) {
			userAddressDto.setPhoneCallingCodes(mobile[0]);
			userAddressDto.setPhoneNumber(mobile[1]);
		} else {
			userAddressDto.setPhoneNumber(mobile[0]);
		}
		return userAddressDto;
	}

	@Override
	public ResultT<Integer> update(UserAddressDto userAddressDto) {
		Map<String, String> paramMap = this.getRequestParam(userAddressDto, ADDRESS_UPDATE_URL);
		JSONObject response = this.request(paramMap, ADDRESS_UPDATE_URL);
		if (response == null || !"true".equals(response.getString("success"))) {
			return ResultT.fail();
		}
		redisCache.delete(ADDRESS_LIST_CACHE + userAddressDto.getSsoid());
		return ResultT.success(1);
	}

	@Override
	public ResultT<Integer> deleteById(long ssoid, long userAddressId) {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setSsoid(ssoid);
		userAddressDto.setId(userAddressId);
		Map<String, String> paramMap = this.getRequestParam(userAddressDto, ADDRESS_DELETE_URL);
		JSONObject response = this.request(paramMap, ADDRESS_DELETE_URL);
		if (response == null || !"true".equals(response.getString("success"))) {
			return ResultT.fail();
		}
		redisCache.delete(ADDRESS_LIST_CACHE + userAddressDto.getSsoid());
		return ResultT.success(1);
	}

	@Override
	public ResultT<Integer> setDefault(UserAddressDto userAddressDto) {
		return null;
	}

	private JSONObject request(Map<String, String> paramMap, String addressUrl) {
		String url = host + addressUrl;
		logger.info("request=" + url + ";param=" + JSONObject.toJSONString(paramMap));
		try {

			JSONObject object = httpClient.postAsJson(url, paramMap, JSONObject.class);
			logger.info("request=" + url + ";response=" + object);
			return object;

		} catch (Exception e) {
			logger.error("request=" + url + e);
		}
		return null;
	}

	private Map<String, String> getRequestParam(UserAddressDto userAddressDto, String url) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", appKey);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		if (ADDRESS_ADD_URL.equals(url) || ADDRESS_LIST_URL.equals(url)) {
			params.put("brand", "REALME");
		}
		if (userAddressDto.getSsoid() != null) {
			params.put("ssoid", "" + userAddressDto.getSsoid());
		}
		if (userAddressDto.getId() != null) {
			params.put("addresssId", "" + userAddressDto.getId());
		}
		params.put("postCode", userAddressDto.getPostCode());
		params.put("contact", userAddressDto.getFullName());
		params.put("countryCode", userAddressDto.getCountyId());
		params.put("countryName", userAddressDto.getCountyName());
		params.put("provinceId", userAddressDto.getProvinceId());
		params.put("provinceName", userAddressDto.getProvinceName());
		params.put("cityId", userAddressDto.getCityId());
		params.put("cityName", userAddressDto.getCityName());
		if (StringUtils.isNotBlank(userAddressDto.getPhoneNumber())) {
			String callCode = StringUtils.isBlank(userAddressDto.getPhoneCallingCodes()) ? ""
					: userAddressDto.getPhoneCallingCodes().trim();
			params.put("mobile", callCode + " " + userAddressDto.getPhoneNumber());
		}
		params.put("detailAddress", userAddressDto.getAddress1());
		params.put("landmark", userAddressDto.getAddress2());
		params.put("email", userAddressDto.getEmail());
		if (userAddressDto.getIsDefault() != null) {
			String defaultFlag = String.valueOf(userAddressDto.getIsDefault())
					.equals(AddressConst.DefaultStatus.DEFAULT.getValue()) ? "YES" : "NO";
			params.put("isDefault", defaultFlag);
		}
		params.put("sign", getSignature(params));
		return params;
	}

	/**
	 * 签名信息，按字典排序，值为空，不参与签名
	 */
	private String getSignature(TreeMap<String, String> params) {
		StringBuilder keyValueStr = new StringBuilder();
		for (String key : params.keySet()) {
			if (StringUtils.isNotBlank(params.get(key))) {
				keyValueStr.append(key).append("=").append(params.get(key)).append("&");
			}
		}
		keyValueStr.append("key=").append(appSecret);
		return DigestUtils.md5Hex(keyValueStr.toString());
	}

	/** 从缓存获取用户收货地址列表 */
	private List<UserAddressDto> getUserAddressDtoListFromCache(long ssoid) {
		List<UserAddressDto> list = redisCache.get(ADDRESS_LIST_CACHE + ssoid);
		if (list == null || list.isEmpty()) {
			return new LinkedList<UserAddressDto>();
		}
		return list;
	}

	/** 保存用户收货地址到缓存中 */
	private void setUserAddressDtoListToCache(Long ssoid, List<UserAddressDto> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		redisCache.set(ADDRESS_LIST_CACHE + ssoid, list);
	}

}
