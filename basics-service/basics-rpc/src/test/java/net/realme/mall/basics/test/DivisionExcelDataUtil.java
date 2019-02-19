package net.realme.mall.basics.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.realme.mall.basics.dto.DivisionDto;

public class DivisionExcelDataUtil {

	public static final long TIME = new Date().getTime();

	public static final Map<String, String> indiaCodeMap = new HashMap<String, String>();
	static {
		indiaCodeMap.put("Andaman And Nicobar Islands", "IN-AN");
		indiaCodeMap.put("Andhra Pradesh", "IN-AP");
		indiaCodeMap.put("Arunachal Pradesh", "IN-AR");
		indiaCodeMap.put("Assam", "IN-AS");
		indiaCodeMap.put("Bihar", "IN-BR");
		indiaCodeMap.put("Chandigarh", "IN-CH");
		indiaCodeMap.put("Chhattisgarh", "IN-CT");
		indiaCodeMap.put("Dadra And Nagar Haveli", "IN-DN");
		indiaCodeMap.put("Daman And Diu", "IN-DD");
		indiaCodeMap.put("Delhi", "IN-DL");
		indiaCodeMap.put("Goa", "IN-GA");
		indiaCodeMap.put("Gujarat", "IN-GJ");
		indiaCodeMap.put("Haryana", "IN-HR");
		indiaCodeMap.put("Himachal Pradesh", "IN-HP");
		indiaCodeMap.put("Jammu And Kashmir", "IN-JK");
		indiaCodeMap.put("Jharkhand", "IN-JH");
		indiaCodeMap.put("Karnataka", "IN-KA");
		indiaCodeMap.put("Kerala", "IN-KL");
		indiaCodeMap.put("Lakshadweep", "IN-LD");
		indiaCodeMap.put("Madhya Pradesh", "IN-MP");
		indiaCodeMap.put("Maharashtra", "IN-MH");
		indiaCodeMap.put("Manipur", "IN-MN");
		indiaCodeMap.put("Meghalaya", "IN-ML");
		indiaCodeMap.put("Mizoram", "IN-MZ");
		indiaCodeMap.put("Nagaland", "IN-NL");
		indiaCodeMap.put("Odisha", "IN-OR");
		indiaCodeMap.put("Pondicherry", "IN-PY");
		indiaCodeMap.put("Punjab", "IN-PB");
		indiaCodeMap.put("Rajasthan", "IN-RJ");
		indiaCodeMap.put("Sikkim", "IN-SK");
		indiaCodeMap.put("Tamil Nadu", "IN-TN");
		indiaCodeMap.put("Telangana", "IN-TG");
		indiaCodeMap.put("Tripura", "IN-TR");
		indiaCodeMap.put("Uttar Pradesh", "IN-UP");
		indiaCodeMap.put("Uttarakhand", "IN-UT");
		indiaCodeMap.put("West Bengal", "IN-WB");
	}

	public static List<DivisionDto> getDivisionDtoListFromExcel() throws IOException {
		XSSFSheet sheet = getXSSFSheet();
		List<DivisionDto> list = getDivisionDtoFrom(sheet);
		return list;
	}

	public static Map<String, String> getDivisionCodeFromExcel() throws IOException {
		XSSFSheet sheet = getXSSFSheet();
		Map<String, String> codeMap = getDivisionCodeFrom(sheet);
		return codeMap;
	}

	private static XSSFSheet getXSSFSheet() throws IOException {
		String fileName = "D:/city.xlsx";
		InputStream is = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	private static List<DivisionDto> getDivisionDtoFrom(XSSFSheet sheet) {
		List<DivisionDto> list = new ArrayList<DivisionDto>();
		Map<String, String> provinceMap = new HashMap<String, String>();
		Map<String, String> cityMap = new HashMap<String, String>();
		List<Map<String, String>> cityList = new ArrayList<Map<String, String>>();
		Map<String, List<String>> provinceCityMap = new LinkedHashMap<String, List<String>>(); // 顺序
		// 循环行Row
		for (int rowNum = 0, lastNum = sheet.getLastRowNum(); rowNum <= lastNum; rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			String provinceName = getStringValueFromCell(row.getCell(0)).trim();
			String cityName = getStringValueFromCell(row.getCell(1)).trim();

			cityMap.put(cityName, provinceName);
			provinceMap.put(provinceName, provinceName);

			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("provinceName", provinceName);
			dataMap.put("cityName", cityName);
			cityList.add(dataMap);
			if (provinceCityMap.containsKey(provinceName)) {
				List<String> tempList = provinceCityMap.get(provinceName);
				tempList.add(cityName);
			} else {
				List<String> tempList = new ArrayList<String>();
				tempList.add(cityName);
				provinceCityMap.put(provinceName, tempList);
			}
		}
		Map<String, String> codeMap = getCode(provinceCityMap);
		for (Map.Entry<String, String> entry : provinceMap.entrySet()) {
			DivisionDto divisionDto = getProvinceDivisionDto(entry.getValue(), codeMap);
			list.add(divisionDto);
		}
		for (Map<String, String> city : cityList) {
			DivisionDto divisionDto = getCityDivisionDto(city, codeMap);
			list.add(divisionDto);
		}
		return list;
	}

	private static Map<String, String> getDivisionCodeFrom(XSSFSheet sheet) {
		Map<String, List<String>> provinceCityMap = new LinkedHashMap<String, List<String>>(); // 顺序
		// 循环行Row
		for (int rowNum = 0, lastNum = sheet.getLastRowNum(); rowNum <= lastNum; rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			String provinceName = getStringValueFromCell(row.getCell(0)).trim();
			String cityName = getStringValueFromCell(row.getCell(1)).trim();
			if (provinceCityMap.containsKey(provinceName)) {
				List<String> tempList = provinceCityMap.get(provinceName);
				tempList.add(cityName);
			} else {
				List<String> tempList = new ArrayList<String>();
				tempList.add(cityName);
				provinceCityMap.put(provinceName, tempList);
			}
		}
		Map<String, String> codeMap = getCode(provinceCityMap);
		return codeMap;
	}

	private static DivisionDto getProvinceDivisionDto(String provinceName, Map<String, String> codeMap) {
		DivisionDto divisionDto = new DivisionDto();
		divisionDto.setCountryCode("IN");
		divisionDto.setDivisionType("province");
		divisionDto.setDivisionId(indiaCodeMap.get(provinceName));
		divisionDto.setDivisionCode(indiaCodeMap.get(provinceName));
		divisionDto.setDivisionName(provinceName);
		divisionDto.setParentId("IN");
		divisionDto.setParentName("India");
		divisionDto.setStatus(Byte.valueOf("1"));
		divisionDto.setSequence(0);
		divisionDto.setCreatedAt(TIME);
		String idPath = "IN:" + indiaCodeMap.get(provinceName);
		divisionDto.setIdPath(idPath);
		String namePath = "IN:" + provinceName;
		divisionDto.setNamePath(namePath);
		return divisionDto;
	}

	private static DivisionDto getCityDivisionDto(Map<String, String> cityMap, Map<String, String> codeMap) {
		String cityName = cityMap.get("cityName");
		String provinceName = cityMap.get("provinceName");
		DivisionDto divisionDto = new DivisionDto();
		divisionDto.setCountryCode("IN");
		divisionDto.setDivisionType("city");
		divisionDto.setDivisionId(codeMap.get(provinceName + "-" + cityName));
		divisionDto.setDivisionCode(codeMap.get(provinceName + "-" + cityName));
		divisionDto.setDivisionName(cityName);
		divisionDto.setParentId(indiaCodeMap.get(provinceName));
		divisionDto.setParentName(provinceName);
		divisionDto.setStatus(Byte.valueOf("1"));
		divisionDto.setSequence(0);
		divisionDto.setCreatedAt(TIME);
		String idPath = "IN:" + indiaCodeMap.get(provinceName) + ":" + codeMap.get(provinceName + "-" + cityName);
		divisionDto.setIdPath(idPath);
		String namePath = "IN:" + provinceName + ":" + cityName;
		divisionDto.setNamePath(namePath);
		return divisionDto;
	}

	private static Map<String, String> getCode(Map<String, List<String>> provinceCityMap) {
		Map<String, String> codeMap = new HashMap<String, String>();
		for (Map.Entry<String, List<String>> entry : provinceCityMap.entrySet()) {
			String provinceName = entry.getKey();
			codeMap.put(provinceName, indiaCodeMap.get(provinceName));

			int cityCode = 1;
			List<String> cityList = entry.getValue();
			for (String cityName : cityList) {
				String value = String.valueOf(cityCode);
				if (cityCode < 10) {
					value = "0" + value;
				}
				value = indiaCodeMap.get(provinceName) + "-" + value;
				codeMap.put(provinceName + "-" + cityName, value);
				cityCode++;
			}
		}
		return codeMap;
	}

	private static String getStringValueFromCell(XSSFCell cell) {
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
			cellValue = cell.getCTCell().getV();
		}
		return cellValue;
	}

}
