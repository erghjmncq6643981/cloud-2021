package com.chandler.config.server.util.rule.operator;


import com.chandler.config.server.util.TimeUtil;

/**
 *
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 * @version 1.0.0
 * @author 钱丁君-chandler 2021/9/29 5:42 下午
 * @since 1.8
 */
public class AfterOperator extends BaseOperator {
    private static final long serialVersionUID = 1;
    @Override
    public String name() {
        return "after";
    }

    @Override
    public Boolean execute(Object[] list) {
        if (isInvalid(list)) {
            return false;
        }
        Long left = TimeUtil.getLongTime(list[0]);
        Long right = TimeUtil.getLongTime(list[1]);
        return left >= right;
    }
}
