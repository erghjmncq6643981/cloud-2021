package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 2021
 *
 * @author luokai 2021/3/25
 * @version 1.0.0
 * @since 1.8
 */
@Getter
public enum FieldCategoryEnum {
    BASIC("BASIC", "基础类型"),
    METADATA("METADATA", "元数据类型"),
    DICT("DICT", "字典类型");

    private String name;
    private String title;

    FieldCategoryEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
