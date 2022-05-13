package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 7/13/21
 * Encoding: UNIX UTF-8
 *
 * @author 钱威
 */
public enum OpenApiOrderEnum {
    ADD("ADD", "新增权限"),
    DELETE("DELETE", "删除权限");

    OpenApiOrderEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }


    @Getter
    private String value;

    @Getter
    private String label;
}
