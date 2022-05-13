package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 2021/4/14
 * Encoding: UNIX UTF-8
 *
 * @author 许鹏
 */
public enum ConfigType {
    /**
     * 字典
     */
    DICT("DICT", "字典配置"),

    /**
     * 系统配置
     */
    SYS_CONF("SYS_CONF", "系统配置"),
    /**
     * 开关配置
     */
    SWITCH("SWITCH", "开关配置"),
    /**
     * 建议版业务配置
     */
    SIM_BIZ_CONF("SIM_BIZ_CONF", "简单业务配置"),
    /**
     * 业务配置
     */
    BIZ_CONF("BIZ_CONF", "业务配置");

    @Getter
    private String label;
    @Getter
    private String value;

    ConfigType(String value, String label) {
        this.label = label;
        this.value = value;
    }
}
