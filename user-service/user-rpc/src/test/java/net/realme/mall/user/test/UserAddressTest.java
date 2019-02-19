package net.realme.mall.user.test;

import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import net.realme.framework.rest.client.RestHttpClient;
import net.realme.mall.user.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserAddressTest {

	public static String HOST = "http://openaccount.ucnewtest.wanyol.com";
	public static String APPKEY = "VVNiWZJAhu6nX7YUaSJcTu";
	public static String APPSCERT = "8l1IMgH+Z/ffMLwt/+994w==";
	public static String USER_ADDRESS_ADD = "/delivery/add-address";
	public static String USER_ADDRESS_LIST = "/delivery/list-address";
	public static String USER_ADDRESS_UPDATE = "/delivery/modify-address";
	public static String USER_ADDRESS_DEFAULT = "/delivery/modify-address";
	public static String USER_ADDRESS_DELETE = "/delivery/delete-address";

	@Autowired
	private RestHttpClient httpClient;

	@Test
	public void addUserAddress() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", APPKEY);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		params.put("brand", "REALME");
		params.put("ssoid", "15452");
		params.put("postCode", "722102");
		params.put("contact", "liutangqi");
		params.put("countryCode", "IN");
		params.put("countryName", "India");
		params.put("provinceId", "IN-WB");
		params.put("provinceName", "West Bengal");
		params.put("cityId", "IN-WB-03");
		params.put("cityName", "Bankura");
		params.put("regionId", "");
		params.put("regionName", "");
		params.put("mobile", "+911545265");
		params.put("detailAddress", "afswerw");
		params.put("landmark", "werwsfsdf");
		params.put("email", "15856462@qq.com");
		params.put("isDefault", "YES");
		params.put("tag", "");
		params.put("sign", getSignature(params));
		System.out.println("sign: " + params.get("sign"));
		try {
			JSONObject object = httpClient.postAsJson(HOST + USER_ADDRESS_ADD, params, JSONObject.class);
			System.out.println(object);
		} catch (RestClientException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getUserAddressList() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", APPKEY);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		params.put("brand", "REALME");
		params.put("ssoid", "15452");
		params.put("sign", getSignature(params));
		try {
			JSONObject object = httpClient.postAsJson(HOST + USER_ADDRESS_LIST, params, JSONObject.class);
			System.out.println(object);
		} catch (RestClientException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void updateUserAddress() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", APPKEY);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		params.put("ssoid", "15452");
		params.put("addresssId", "108285651576832000");
		params.put("postCode", "722102");
		params.put("contact", "liutangqi");
		params.put("countryCode", "IN");
		params.put("countryName", "India");
		params.put("provinceId", "IN-WB");
		params.put("provinceName", "West Bengal");
		params.put("cityId", "IN-WB-03");
		params.put("cityName", "Bankura");
		params.put("regionId", "");
		params.put("regionName", "");
		params.put("mobile", "+911545265");
		params.put("detailAddress", "afswerw");
		params.put("landmark", "werwsfsdf");
		params.put("email", "15856462@qq.com");
		params.put("isDefault", "YES");
		params.put("tag", "");
		params.put("sign", getSignature(params));
		System.out.println("sign: " + params.get("sign"));
		try {
			JSONObject object = httpClient.postAsJson(HOST + USER_ADDRESS_UPDATE, params, JSONObject.class);
			System.out.println(object);
		} catch (RestClientException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void defaultUserAddress() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", APPKEY);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		params.put("ssoid", "15452");
		params.put("postCode", "722102");
		params.put("contact", "liutangqi");
		params.put("countryCode", "IN");
		params.put("countryName", "India");
		params.put("provinceId", "IN-WB");
		params.put("provinceName", "West Bengal");
		params.put("cityId", "IN-WB-03");
		params.put("cityName", "Bankura");
		params.put("regionId", "");
		params.put("regionName", "");
		params.put("mobile", "+911545265");
		params.put("detailAddress", "afswerw");
		params.put("landmark", "werwsfsdf");
		params.put("email", "15856462@qq.com");
		params.put("isDefault", "YES");
		params.put("tag", "");
		params.put("sign", getSignature(params));
		System.out.println("sign: " + params.get("sign"));
		try {
			JSONObject object = httpClient.postAsJson(HOST + USER_ADDRESS_UPDATE, params, JSONObject.class);
			System.out.println(object);
		} catch (RestClientException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void deleteUserAddress() {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("appKey", APPKEY);
		params.put("timestamp", "" + System.currentTimeMillis());
		params.put("signType", "md5");
		params.put("ssoid", "15452");
		params.put("addresssId", "");
		params.put("sign", getSignature(params));
		System.out.println("sign: " + params.get("sign"));
		try {
			JSONObject object = httpClient.postAsJson(HOST + USER_ADDRESS_DELETE, params, JSONObject.class);
			System.out.println(object);
		} catch (RestClientException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getSignature(TreeMap<String, String> params) {
		StringBuilder keyValueStr = new StringBuilder();
		for (String key : params.keySet()) {
			if (StringUtils.isNotEmpty(params.get(key))) {
				keyValueStr.append(key).append("=").append(params.get(key)).append("&");
			}
		}
		keyValueStr.append("key=").append(APPSCERT);
		System.out.println("keyValueStr: " + keyValueStr);
		return DigestUtils.md5Hex(keyValueStr.toString());
	}

}
