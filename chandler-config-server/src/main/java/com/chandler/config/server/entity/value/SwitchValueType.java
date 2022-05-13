/*
 * configservice-parent
 * 2021/4/8 5:55 PM
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

import lombok.Getter;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/4/8 5:55 PM
 * @version 1.0.0
 * @since 1.8
 */
@Getter
public enum SwitchValueType {
    STRING("STRING", "字符类型"),
    INTEGER("INTEGER", "数值类型"),
    BOOLEAN("BOOLEAN", "布尔类型");

    SwitchValueType(String type, String label) {
        this.type = type;
        this.label = label;
    }

    private String type;
    private String label;

    public static  SwitchValueType valueOfString(String name){
        for (SwitchValueType t : SwitchValueType.values()) {
            if (t.getType().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}
