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
public enum ConstraintEnum {
    REQUIRED("REQUIRED", "是否必须"),
    UNIQUE("UNIQUE", "是否唯一"),
    MIN("MIN", "最小值"),
    MAX("MAX", "最大值"),
    LENGTH("LENGTH", "长度"),
    REGEX("REGEX", "正则式"),
    WEB_HOOK("WEB_HOOK", "WEB_HOOK");

    private String name;
    private String title;

    ConstraintEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
