package com.jdc.onlineshopping.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tiendao on 17/07/2021
 */
public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final SimpleDateFormat STR_DDMMYY_TIME_FORMAT = new SimpleDateFormat("ddMMyy");
    public static final String STR_DEFAULT_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String STR_DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String STR_TIME_WITH_TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final SimpleDateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat(STR_DEFAULT_TIME_FORMAT);
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(STR_DEFAULT_DATE_FORMAT);
    public static final ZoneId HCMC_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.from(ZoneOffset.UTC);

    /**
     * Convert a local date in Vietnam time to start time or end time in UTC
     * This function is used for searching condition by date range, and the date value is in Vietnam time
     * For example, localDateStr is Jun 22, 2021, then utc start time should be Jun 21, 2021 17:00:00 UTC, and the end time should be Jun 22, 2021 16:59:59 UTC
     * @param localDateStr in Vietnam time string
     * @param isEndOfDay if want to get time of end day in UTC time
     * @return
     */
    public static Instant convertIctDateToUtcInstant(String localDateStr, boolean isEndOfDay) {
        if (ObjectUtils.isEmpty(localDateStr)) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(STR_DEFAULT_DATE_FORMAT));
        LocalDateTime localDateTime = isEndOfDay ? LocalDateTime.of(localDate, LocalTime.MAX) : LocalDateTime.of(localDate, LocalTime.MIN);
        return convertIctToUtc(localDateTime);
    }

    /**
     * Convert an ICT LocalDateTime to UTC Instant
     * @param ictDateTime
     * @return
     */
    public static Instant convertIctToUtc(LocalDateTime ictDateTime) {
        return ictDateTime.atZone(HCMC_ZONE).toInstant();
    }

    public static Instant convertIctDateTimeToUtcInstant(String localDateTimeStr) {
        if (ObjectUtils.isEmpty(localDateTimeStr)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(STR_DEFAULT_TIME_FORMAT));
        return convertIctToUtc(localDateTime);
    }

    public static String convertUtcInstantToStringGmt(Instant utcInstant) {
        String result = "";
        if (null == utcInstant) {
            return result;
        }
        try {
            DateTimeFormatter formatterZ = DateTimeFormatter.ofPattern(DateUtils.STR_TIME_WITH_TIMEZONE_FORMAT);
            result = formatterZ.format(utcInstant.atZone(HCMC_ZONE));
        } catch (Exception e) {
            log.warn("DateUtils.convertUtcInstantToStringGmt:" + e.getMessage());
        }
        return result;
    }

    public static Instant stringToTime(String timeString) {

        try {
            return DEFAULT_TIME_FORMAT.parse(timeString).toInstant();
        } catch (Exception e) {
            log.warn("DateUtils.stringToTime:" + e.getMessage());
            return null;
        }
    }

    public static Instant stringToDate(String timeString) {

        try {
            return DEFAULT_DATE_FORMAT.parse(timeString).toInstant();
        } catch (Exception e) {
            log.warn("DateUtils.stringToDate:" + e.getMessage());
            return null;
        }
    }

    public static long stringToLong(String timeString) {
        Instant instant = stringToTime(timeString);
        if (instant == null) {
            return 0;
        }
        return instant.toEpochMilli();
    }

    public static long stringDDMMYYYYToLong(String timeString) {
        Instant instant = stringToDate(timeString);
        if (instant == null) {
            return 0;
        }
        return instant.toEpochMilli();
    }

    public static Date longToDate(long time) {
        return new Date(time);
    }

    public static Instant longToTime(long time) {
        Timestamp ts = new Timestamp(time);
        return ts.toInstant();
    }

    public static String getNowAsDDMMYYNoSlashString() {
        return STR_DDMMYY_TIME_FORMAT.format(Calendar.getInstance().getTime());
    }
    public static boolean isSameDay(Instant ist, Instant istOther, ZoneId zoneId) {
        if (zoneId == null) {
            zoneId = DEFAULT_ZONE_ID;
        }
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(ist, zoneId).truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime ofInstantOther = ZonedDateTime.ofInstant(istOther, zoneId).truncatedTo(ChronoUnit.DAYS);
        return ofInstant.getDayOfYear() - ofInstantOther.getDayOfYear() == 0;
    }

    public static int getDiffHourForInstant(Instant ist, Instant istOther, ZoneId zoneId) {
        if (zoneId == null) {
            zoneId = DEFAULT_ZONE_ID;
        }
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(ist, zoneId).truncatedTo(ChronoUnit.HOURS);
        ZonedDateTime ofInstantOther = ZonedDateTime.ofInstant(istOther, zoneId).truncatedTo(ChronoUnit.HOURS);
        return Math.abs(ofInstant.getHour() - ofInstantOther.getHour());
    }

    public static Instant stringDDMMYYYYToInstant(String date) {
        return longToTime(stringDDMMYYYYToLong(date));
    }

    public static String timeToString(Instant time) {
        return DEFAULT_TIME_FORMAT.format(Date.from(time));
    }
}
