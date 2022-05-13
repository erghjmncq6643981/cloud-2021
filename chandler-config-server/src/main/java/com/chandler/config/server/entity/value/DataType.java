/*
 * kyle-rabbitmq
 * 2021/3/10 6:48 PM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.value;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/10 6:48 PM
 * @version 1.0.0
 * @since 1.8
 */
public enum  DataType {
    STRING, INTEGER, TIME, NULL;
    private static final String REGEX = "-";


    /**
     * STRING->S、INTEGER->I、TIME->T；支持多种通过 - 链接，比如支持STRING和INTEGER，就是S-I
     */
    public static String getTypeStr(List<DataType> types) {
        StringBuilder builder = new StringBuilder();
        String abb = types.get(0).name();
        builder.append(abb);
        if (types.size() > 1) {
            for (DataType t : types) {
                if (!abb.equalsIgnoreCase(t.name())) {
                    builder.append(REGEX);
                    builder.append(t.name());
                }
            }
        }
        return builder.toString();
    }

    public static List<DataType> discernType(String types) {
        String[] dataTypes = types.split(REGEX);

        return Stream.of(dataTypes).map(d -> {
            for (DataType t : DataType.values()) {
                if (t.name().equalsIgnoreCase(d)) {
                    return t;
                }
            }
            return NULL;
        }).collect(Collectors.toList());
    }

    public static  DataType valueOfString(String type){
        for (DataType t : DataType.values()) {
            if (t.name().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return NULL;
    }

    public static String discernOneType(String type) {
        for (DataType t : DataType.values()) {
            if (t.name().equalsIgnoreCase(type)) {
                return t.name();
            }
        }
        return NULL.name();
    }
}
