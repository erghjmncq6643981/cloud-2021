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
public enum DataOperationEnum {
    NEW("NEW", "新增"),
    MODIFY("MODIFY", "修改"),
    DELETE("DELETE", "删除"),
    WITHDRAW("WITHDRAW", "撤销"),
    ASSOCIATE("ASSOCIATE", "关联"),
    DISASSOCIATE("DISASSOCIATE", "解除关联");

    private String name;
    private String title;

    DataOperationEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
