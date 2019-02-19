package net.realme.mall.basics.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.realme.mall.basics.dto.ServiceSiteDto;

public class SiteExcelDataUtil {

	public static List<ServiceSiteDto> getServiceSiteDtoListFromExcel() throws IOException {
		XSSFSheet sheet = getXSSFSheet();
		List<ServiceSiteDto> list = getServiceSiteDtoFrom(sheet);
		return list;
	}

	public static XSSFSheet getXSSFSheet() throws IOException {
		String fileName = "D:/Aug-2018 Service Center Verfication Report.xlsx";
		InputStream is = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(2);
		return sheet;
	}

	public static List<ServiceSiteDto> getServiceSiteDtoFrom(XSSFSheet sheet) {
		List<ServiceSiteDto> list = new ArrayList<ServiceSiteDto>();
		// 循环行Row
		for (int rowNum = 1, lastNum = sheet.getLastRowNum(); rowNum <= lastNum; rowNum++) {
			ServiceSiteDto serviceSiteDto = getServiceSiteDto(sheet.getRow(rowNum));
			if (serviceSiteDto == null) {
				continue;
			}
			list.add(serviceSiteDto);
		}
		return list;
	}

	public static ServiceSiteDto getServiceSiteDto(XSSFRow row) {
		String countryName = getStringValueFromCell(row.getCell(0));
		if (StringUtils.isBlank(countryName)) {
			return null;
		}
		String provinceName = getStringValueFromCell(row.getCell(1));
		String cityName = getStringValueFromCell(row.getCell(4));
		String name = getStringValueFromCell(row.getCell(5));
		String address = getStringValueFromCell(row.getCell(6));
		String phoneNumberStr = getStringValueFromCell(row.getCell(7));
		String openTimeStr = getStringValueFromCell(row.getCell(8));
		String postCode = getStringValueFromCell(row.getCell(10));
		String latitude = getStringValueFromCell(row.getCell(11)); // 纬度
		String longitude = getStringValueFromCell(row.getCell(12)); // 经度

		ServiceSiteDto serviceSiteDto = new ServiceSiteDto();
		serviceSiteDto.setSiteCode("in");
		serviceSiteDto.setCountryName(countryName);
		serviceSiteDto.setProvinceName(provinceName);
		serviceSiteDto.setCityName(cityName);
		serviceSiteDto.setName(name);
		serviceSiteDto.setAddress(address);
		/*String phoneCallingCodes = phoneNumberStr.substring(0, 3);
		serviceSiteDto.setPhoneCallingCodes(phoneCallingCodes);*/
		String phoneNumber = getPhoneNumber(phoneNumberStr);
		serviceSiteDto.setPhoneNumber(phoneNumber);
		serviceSiteDto.setPostCode(postCode);
		serviceSiteDto.setLatitude(latitude);
		serviceSiteDto.setLongitude(longitude);
		String openTimeWeek = getOpenTimeWeek(openTimeStr);
		serviceSiteDto.setOpenTimeWeek(openTimeWeek);
		String openTime = getOpenTime(openTimeStr);
		serviceSiteDto.setOpenTime(openTime);
		serviceSiteDto.setStatus(Byte.valueOf("1"));
		serviceSiteDto.setType(Byte.valueOf("1"));
		serviceSiteDto.setOwnerType(Byte.valueOf("2")); // 归属oppo
		serviceSiteDto.setAssessStatus(Byte.valueOf("2"));
		serviceSiteDto.setAssessType(Byte.valueOf("1"));
		Long time = new Date().getTime();
		serviceSiteDto.setCreatedAt(time);
		serviceSiteDto.setUpdatedAt(time);
		return serviceSiteDto;
	}

	public static String getStringValueFromCell(XSSFCell cell) {
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

	public static String getPhoneNumber(String phoneNumberStr) {
		String phoneCallingCodes = phoneNumberStr.substring(0, 4);
		String phoneNumber = "";
		String[] phoneArry = phoneNumberStr.split("\\+");
		for (int i = 0; i < phoneArry.length; i++) {
			String phone = phoneArry[i].trim();
			if (StringUtils.isBlank(phone)) {
				continue;
			}
			String houzuiPhone = (phone.substring(3, phone.length())).replaceAll(" ", "");
			phoneNumber = phoneNumber + ";" + phoneCallingCodes + houzuiPhone;
		}
		System.out.println("-----------" + phoneNumber.substring(1));
		return phoneNumber.substring(1);
	}

	public static String getOpenTimeWeek(String openTimeStr) {
		int index = getDigestIndex(openTimeStr);
		String weekStr = openTimeStr.substring(0, index);
		String week = weekStr.replaceAll(" to ", ",");
		return week;
	}

	public static String getOpenTime(String openTimeStr) {
		int index = getDigestIndex(openTimeStr);
		String timeStr = openTimeStr.substring(index);
		String time = timeStr.replaceAll(" - ", ",");
		time = time.replaceAll(" AM", "");
		time = time.replaceAll(" PM", "");
		return time;
	}

	public static int getDigestIndex(String str) {
		int digestIndex = 0;
		for (int i = 0, len = str.length(); i < len; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				digestIndex = i;
				break;
			}
		}
		return digestIndex;
	}
}
