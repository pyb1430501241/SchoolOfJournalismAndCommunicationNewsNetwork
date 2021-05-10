package com.pdsu.sojacnn.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 半梦
 * @create 2021-05-10 18:59
 */
public abstract class DateUtils {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    @NonNull
    public static Date nowDate() {
        return create(System.currentTimeMillis());
    }

    @NonNull
    public static Date create(Long time) {
        return new Date(time);
    }

    @NonNull
    public static String dateString(@NonNull Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    @Nullable
    public static Date formatDate(@NonNull String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
