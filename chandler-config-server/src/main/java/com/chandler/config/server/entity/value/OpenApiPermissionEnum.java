package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 7/14/21
 * Encoding: UNIX UTF-8
 *
 * @author 钱威
 */
public enum OpenApiPermissionEnum {

    DIRECT_WRITE("DIRECT_WRITE", "跳过工单"),

    SIM_BIZ_CONFIGS_READ("SIM_BIZ_CONFIGS_READ", "简易业务配置读"),

    SUBMIT_ORDER("SUBMIT_ORDER", "工单提交");



    OpenApiPermissionEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }


    @Getter
    private String value;

    @Getter
    private String label;
}
