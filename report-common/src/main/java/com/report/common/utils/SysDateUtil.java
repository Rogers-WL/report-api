package com.report.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wl
 */
public class SysDateUtil {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATETIME_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String toDateTimeString(LocalDateTime dateTime) {
        return dateTime == null ? "" : DATETIME_FORMATTER.format(dateTime);
    }

    public static LocalDateTime toDateTime(String dateTimeString) {
        return StrUtil.isBlank(dateTimeString) ? null : LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
    }

    public static String toDateString(LocalDate date) {
        return date == null ? "" : DATE_FORMATTER.format(date);
    }

    public static String toDateString(LocalDateTime dateTime) {
        return dateTime == null ? "" : DATE_FORMATTER.format(dateTime);
    }

    public static String toDateTimeMinuteString(LocalDateTime dateTime) {
        return dateTime == null ? "" : DATETIME_MINUTE_FORMATTER.format(dateTime);
    }

    public static String toTimeMinuteString(LocalTime time) {
        return time == null ? "" : TIME_MINUTE_FORMATTER.format(time);
    }

    public static String toTimeMinuteString(LocalDateTime dateTime) {
        return dateTime == null ? "" : TIME_MINUTE_FORMATTER.format(dateTime);
    }


    public static LocalDateTime toDateTimeMinute(String dateTimeString) {
        return StrUtil.isBlank(dateTimeString) ? null : LocalDateTime.parse(dateTimeString, DATETIME_MINUTE_FORMATTER);
    }

    public static LocalDate toDate(String dateString) {
        return StrUtil.isBlank(dateString) ? null : LocalDate.parse(dateString, DATE_FORMATTER);
    }

}
