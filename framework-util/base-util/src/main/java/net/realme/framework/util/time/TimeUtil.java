package net.realme.framework.util.time;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author 91000044
 */
public class TimeUtil {

    /**
     *
     * 按指定时区指定格式解析时间字符串
     * @param str 时间值字符串
     * @param format 格式
     * @param zoneId 时区标识
     * @return
     */
    public static long localStrToTimestamp(String str, String format, String zoneId) {
        ZoneId timezone = ZoneId.of(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(timezone);
        ZonedDateTime zdt = ZonedDateTime.parse(str, formatter).withZoneSameInstant(ZoneOffset.UTC);
        return Timestamp.from(zdt.toInstant()).getTime();
    }

    /**
     *
     * 生成指定时区指定格式的时间字符串
     * @param timestamp 时间值
     * @param format 格式
     * @param zoneId 时区标识
     * @return
     */
    public static String timestampToLocalStr(long timestamp, String format, String zoneId) {
        ZoneId timezone = ZoneId.of(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), timezone);
        return localDateTime.format(formatter);
    }

    /**
     *
     * 当前时间生成指定时区指定格式的时间字符串
     * @param format 格式
     * @param zoneId 时区标识
     * @return
     */
    public static String nowToLocalStr(String format, String zoneId) {
        return timestampToLocalStr(System.currentTimeMillis(), format, zoneId);
    }


    public static void main(String[] args) {
        long a = localStrToTimestamp("03-09-2018 18:45:06", "dd-MM-yyyy HH:mm:ss", TimeZoneConstant.ZONE_INDIA);
        System.out.println(a);
        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(a), ZoneId.systemDefault());
        System.out.println(date);

        System.out.println(timestampToLocalStr(System.currentTimeMillis(), "yyyyMMddHHmmss", TimeZoneConstant.ZONE_INDIA));
    }
}
