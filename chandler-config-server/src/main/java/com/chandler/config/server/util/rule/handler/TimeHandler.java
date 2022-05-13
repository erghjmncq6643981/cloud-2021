/*
 * kyle-rabbitmq
 * 2021/3/15 11:45 AM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.util.rule.handler;

import com.chandler.config.server.entity.Constant;
import com.chandler.config.server.entity.bo.rule.RuleOperationBo;
import com.chandler.config.server.entity.bo.rule.RuleParamBo;
import com.chandler.config.server.entity.bo.rule.RulePatternBo;
import com.chandler.config.server.entity.value.DataType;
import com.chandler.config.server.entity.value.OperationType;

import static com.chandler.config.server.entity.value.RuleConstant.*;

import com.chandler.config.server.exception.RuleException;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;



/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/15 11:45 AM
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
public class TimeHandler implements Handler {

    @Override
    public String handle(String wild, RulePatternBo pattern) {
        split(wild, OperationType.greater, pattern);
        if (!Objects.isNull(pattern.getOperation())) {
            pattern.getOperation().setOperation(after);
        } else {
            split(wild, OperationType.less, pattern);
            pattern.getOperation().setOperation(before);
        }
        pattern.getParam().setType(DataType.TIME);

        Date time;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(pattern.getValue());
        } catch (ParseException e) {
            throw new RuleException(1001, String.format("%s规则转换时间格式失败，异常信息：%s！", pattern, e));
        }
        DateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
        String value = format.format(time);
        pattern.setValue(value);
        return wild;
    }

    @Override
    public String rollback(RulePatternBo pattern) {
        if (!pattern.getParam().getAbbreviation().equalsIgnoreCase(timesamp)) {
            return new NumberHandler().rollback(pattern);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(timesamp);
        String operation = pattern.getOperation().getOperation();
        if (after.equals(operation)) {
            operation = OperationType.greater.getOperation();
        }
        if (before.equals(operation)) {
            operation = OperationType.less.getOperation();
        }
        builder.append(operation);

        Date date;
        try {
            date = new SimpleDateFormat(Constant.DATE_FORMAT).parse(pattern.getValue());
        } catch (ParseException e) {
            throw new RuleException(1001, String.format("%s规则转换时间格式失败，异常信息：%s！", pattern.toString(), e));
        }
        String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);
        builder.append(time);
        return builder.toString();
    }

    @Override
    public Boolean match(String param) {
        return param.startsWith(timesamp);
    }
}
