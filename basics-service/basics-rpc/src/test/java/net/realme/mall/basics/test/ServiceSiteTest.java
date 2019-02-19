package net.realme.mall.basics.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.Application;
import net.realme.mall.basics.dto.IndiaPinCodeDto;
import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.facade.IndiaPinCodeService;
import net.realme.mall.basics.facade.ServiceSiteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceSiteTest {

	@Autowired
	private ServiceSiteService serviceSiteService;

	@Autowired
	private IndiaPinCodeService indiaPinCodeService;

	@Test
	public void addServiceSite() {
		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		serviceSiteDto.setCountryId("");
		serviceSiteDto.setCountryName("India");
		serviceSiteDto.setProvinceId("");
		serviceSiteDto.setProvinceName("sialie");
		serviceSiteDto.setCityId("");
		serviceSiteDto.setCityName("sike");
		serviceSiteDto.setName("sfhowef");
		serviceSiteDto.setSiteCode("in");
		serviceSiteDto.setAddress("sgegw");
		/* serviceSiteDto.setPhoneCallingCodes("+91"); */
		serviceSiteDto.setPhoneNumber("5452541");
		serviceSiteDto.setStatus(Byte.valueOf("1"));
		serviceSiteDto.setType(Byte.valueOf("1"));
		serviceSiteDto.setRepairLevel(Byte.valueOf("1"));
		serviceSiteDto.setOpenTimeWeek("Satureday,Mondary");
		serviceSiteDto.setOpenTime("09:12,16:23");
		serviceSiteDto.setCloseTime("");
		serviceSiteDto.setRemark("");
		serviceSiteDto.setCreatedBy(null);
		serviceSiteDto.setUpdatedBy(null);
		Long time = new Date().getTime();
		serviceSiteDto.setCreatedAt(time);
		serviceSiteDto.setUpdatedAt(time);
		serviceSiteService.addServiceSite(serviceSiteDto);
	}

	@Test
	public void getServiceSiteList() {
		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		String countryName = "India";
		serviceSiteDto.setCountryName(countryName);
		ResultT<ResultList<ServiceSiteDto>> list = serviceSiteService.list(serviceSiteDto, 1, 10);
		System.out.println(list.getData().getRecords());
	}

	@Test
	public void batchAddServiceSite() {
		List<ServiceSiteDto> serviceSiteDtos = new ArrayList<ServiceSiteDto>();
		for (int i = 0; i < 2; i++) {
			ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
			serviceSiteDto.setCountryId("");
			serviceSiteDto.setCountryName("India");
			serviceSiteDto.setProvinceId("");
			serviceSiteDto.setProvinceName("sialie");
			serviceSiteDto.setCityId("");
			serviceSiteDto.setCityName("sike");
			serviceSiteDto.setName("sfhowef");
			serviceSiteDto.setSiteCode("in");
			serviceSiteDto.setAddress("sgegw");
			/* serviceSiteDto.setPhoneCallingCodes("+91"); */
			serviceSiteDto.setPhoneNumber("5452541");
			serviceSiteDto.setStatus(Byte.valueOf("1"));
			serviceSiteDto.setType(Byte.valueOf("1"));
			serviceSiteDto.setRepairLevel(Byte.valueOf("1"));
			serviceSiteDto.setOpenTimeWeek("Satureday,Mondary");
			serviceSiteDto.setOpenTime("09:12,16:23");
			serviceSiteDto.setCloseTime("");
			serviceSiteDto.setRemark(null);
			serviceSiteDto.setCreatedBy(null);
			serviceSiteDto.setUpdatedBy(null);
			Long time = new Date().getTime();
			serviceSiteDto.setCreatedAt(time);
			serviceSiteDto.setUpdatedAt(time);
			serviceSiteDtos.add(serviceSiteDto);
		}
		serviceSiteService.batchAddServiceSite(serviceSiteDtos);
	}

	@Test
	public void batchAddServiceSiteFromExcel() throws IOException {
		List<ServiceSiteDto> serviceSiteDtos = SiteExcelDataUtil.getServiceSiteDtoListFromExcel();
		serviceSiteService.batchAddServiceSite(serviceSiteDtos);
	}

	@Test
	public void batchUpdateServiceSite() {
		ResultT<ResultList<IndiaPinCodeDto>> codeResult = indiaPinCodeService.listAll();
		List<IndiaPinCodeDto> pincodeList = codeResult.getData().getRecords();
		Map<String, IndiaPinCodeDto> pincodeMap = new HashMap<String, IndiaPinCodeDto>(20000);
		for (IndiaPinCodeDto pinCodeDto : pincodeList) {
			pincodeMap.put(pinCodeDto.getPinCode(), pinCodeDto);
		}

		ResultT<ResultList<ServiceSiteDto>> result = serviceSiteService.listAll();
		List<ServiceSiteDto> serviceSitelist = result.getData().getRecords();
		for (ServiceSiteDto serviceSiteDto : serviceSitelist) {
			String postCode = serviceSiteDto.getPostCode();
			String provinceName = serviceSiteDto.getProvinceName();
			String cityName = serviceSiteDto.getCityName();

			IndiaPinCodeDto pinCodeDto = pincodeMap.get(postCode);
			if (pinCodeDto == null) {
				System.out.println(provinceName + "-" + cityName + "-" + postCode
						+ " (service site postcode not exist in indiapincode)");
				continue;
			}
			if (StringUtils.isBlank(serviceSiteDto.getCityId())) {
				serviceSiteDto.setCountryId(pinCodeDto.getCountryId());
				serviceSiteDto.setCountryName(pinCodeDto.getCountryName());
				serviceSiteDto.setProvinceId(pinCodeDto.getProvinceId());
				serviceSiteDto.setProvinceName(pinCodeDto.getProvinceName());
				serviceSiteDto.setCityId(pinCodeDto.getCityId());
				serviceSiteDto.setCityName(pinCodeDto.getCityName());
				serviceSiteService.updateServiceSite(serviceSiteDto);
			}
			/*if (provinceName.equals(pinCodeDto.getProvinceName()) && cityName.equals(pinCodeDto.getCityName())) {
				System.out.println(provinceName + "-" + cityName + "-" + postCode + " | " + pinCodeDto.getProvinceName()
						+ "-" + pinCodeDto.getCityName() + "-" + pinCodeDto.getPinCode() + " (same province and city)");
				serviceSiteDto.setCountryId(pinCodeDto.getCountryId());
				serviceSiteDto.setCountryName(pinCodeDto.getCountryName());
				serviceSiteDto.setProvinceId(pinCodeDto.getProvinceId());
				serviceSiteDto.setProvinceName(pinCodeDto.getProvinceName());
				serviceSiteDto.setCityId(pinCodeDto.getCityId());
				serviceSiteDto.setCityName(pinCodeDto.getCityName());
				serviceSiteService.updateServiceSite(serviceSiteDto);
			}*/
		}
	}

	@Test
	public void filterServiceSite() {
		ResultT<ResultList<IndiaPinCodeDto>> codeResult = indiaPinCodeService.listAll();
		List<IndiaPinCodeDto> pincodeList = codeResult.getData().getRecords();
		Map<String, IndiaPinCodeDto> pincodeMap = new HashMap<String, IndiaPinCodeDto>(20000);
		for (IndiaPinCodeDto pinCodeDto : pincodeList) {
			pincodeMap.put(pinCodeDto.getPinCode(), pinCodeDto);
		}

		ResultT<ResultList<ServiceSiteDto>> result = serviceSiteService.listAll();
		List<ServiceSiteDto> serviceSitelist = result.getData().getRecords();
		int total = 0;
		int provinceNum = 0;
		int cityNum = 0;
		for (ServiceSiteDto serviceSiteDto : serviceSitelist) {
			String postCode = serviceSiteDto.getPostCode();
			String provinceName = serviceSiteDto.getProvinceName();
			String cityName = serviceSiteDto.getCityName();

			IndiaPinCodeDto pinCodeDto = pincodeMap.get(postCode);
			if (pinCodeDto == null) {
				System.out.println(provinceName + "-" + cityName + "-" + postCode
						+ " (service site postcode not exist in indiapincode)");
				total++;
				continue;
			}
			if (!provinceName.equals(pinCodeDto.getProvinceName())) {
				System.out.println(provinceName + "-" + cityName + "-" + postCode + " | " + pinCodeDto.getProvinceName()
						+ "-" + pinCodeDto.getCityName() + "-" + pinCodeDto.getPinCode() + " (not same province)");
				provinceNum++;
				total++;
				continue;
			}
			if (!cityName.equals(pinCodeDto.getCityName())) {
				System.out.println(provinceName + "-" + cityName + "-" + postCode + " | " + pinCodeDto.getProvinceName()
						+ "-" + pinCodeDto.getCityName() + "-" + pinCodeDto.getPinCode() + " (not same city)");
				cityNum++;
				total++;
				continue;
			}
		}
		System.out.println(total + ":" + provinceNum + ":" + cityNum);
	}
}
