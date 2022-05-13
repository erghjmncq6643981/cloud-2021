package com.chandler.config.server.entity.value;

import lombok.Getter;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c) 2021
 *
 * @author luokai 2021/4/21
 * @version 1.0.0
 * @since 1.8
 */
@Getter
public enum ApprovalNodeEnum {
    SUPERIOR("SUPERIOR", "上级"),
    DEPARTMENT("DEPARTMENT", "部门主管"),
    CENTER("CENTER", "中心主管");

    private String name;
    private String title;

    ApprovalNodeEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
