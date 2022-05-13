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
public class InPeriodOperator extends BaseOperator {
    private static final long serialVersionUID = 1;

    @Override
    public String name() {
        return "inPeriod";
    }

    @Override
    public Boolean execute(Object[] list) {
        boolean result = false;
        if (isInvalid(list)) {
            return result;
        }
        Long left = TimeUtil.getLongTime(list[0]);
        String[] times = (String[]) list[1];
        if (times.length == 2) {
            Long start = TimeUtil.getLongTime(times[0]);
            Long end = TimeUtil.getLongTime(times[1]);
            //支持闭区间
            result = left >= start && left <= end;
        }
        return result;
    }
}
