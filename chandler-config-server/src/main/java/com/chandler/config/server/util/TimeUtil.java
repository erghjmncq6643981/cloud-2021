package com.chandler.config.server.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    TimeUtil(){}
    public static Long getLongTime(Object time) {
        if (time instanceof String) {
            return LocalDateTime.parse((String) time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        } else {
            return (Long) time;
        }
    }
}
