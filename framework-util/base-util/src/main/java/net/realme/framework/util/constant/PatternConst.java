package net.realme.framework.util.constant;

public final class PatternConst {

	/** 经度正则表达式 */
	public static String LONGITUDE = "^[\\-\\+]?(0?\\d{1,2}|0?\\d{1,2}\\.\\d{1,15}|1[0-7]?\\d{1}|1[0-7]?\\d{1}\\.\\d{1,15}|180|180\\.0{1,15})$";

	/** 纬度正则表达式 */
	public static String LATITUDE = "^[\\-\\+]?([0-8]?\\d{1}|[0-8]?\\d{1}\\.\\d{1,15}|90|90\\.0{1,15})$";

	/** 正整数正则表达式 */
	public static String INTEGER = "^[0-9]*[1-9][0-9]*$";
	
}
