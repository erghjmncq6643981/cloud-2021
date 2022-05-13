package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 2021
 *
 * @author luokai 2021/4/15
 * @version 1.0.0
 * @since 1.8
 */
@Getter
public enum  FieldTypeEnum {
    STRING("STRING", "文本"),
    NUMBER("NUMBER", "数值"),
    DOUBLE("DOUBLE", "浮点数"),
    BIGDECIMAL("BIGDECIMAL", "大浮点数"),
    BOOLEAN("BOOLEAN", "布尔值"),
    DATE("DATE", "日期"),
    TIME("TIME", "时间"),
    DATETIME("DATETIME", "日期时间");

    private String name;
    private String title;

    FieldTypeEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
