/*
 * config-starter-core
 * 2021/9/28 2:32 下午
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.util.rule.operator;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/9/28 2:32 下午
 * @version 1.0.0
 * @since 1.8
 */
public class LikeOperator extends BaseOperator {
    private static final long serialVersionUID = 1;

    public LikeOperator() {
        this.name = "like";
        this.aliasName = "匹配";
    }

    @Override
    public String name() {
        return "like";
    }

    @Override
    public Boolean execute(Object[] list) {
        String param = String.valueOf(list[0]);
        String value = String.valueOf(list[1]);
        boolean result = true;
        if (value.contains("%")) {
            String cleanValue = value;
            boolean start = value.startsWith("%");
            if (start) {
                cleanValue = cleanValue.substring(1);
            }
            boolean end = value.endsWith("%");
            if (end) {
                cleanValue = cleanValue.substring(0, cleanValue.length() - 1);
            }
            if (start) {
                if (end) {
                    result = param.contains(cleanValue);
                } else {
                    result = param.endsWith(cleanValue);
                }
            } else {
                result = param.startsWith(cleanValue);
            }
        } else if (!param.equals(value)) {
            result = false;
        }
        return result;
    }
}
