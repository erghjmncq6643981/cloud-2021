package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 2021
 *
 * @author luokai 2021/3/31
 * @version 1.0.0
 * @since 1.8
 */
@Getter
public enum FieldAssociatedEnum {
    STRUCTURE("STRUCTURE", "结构"),
    DATA("DATA", "数据");

    private String name;
    private String title;

    FieldAssociatedEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
