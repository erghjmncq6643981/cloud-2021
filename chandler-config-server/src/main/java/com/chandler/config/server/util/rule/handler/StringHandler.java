/*
 * kyle-rabbitmq
 * 2021/3/15 11:44 AM
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


import com.chandler.config.server.entity.bo.rule.RuleFunctionBo;
import com.chandler.config.server.entity.bo.rule.RulePatternBo;
import com.chandler.config.server.entity.value.DataType;
import com.chandler.config.server.entity.value.OperationType;

import java.util.Objects;

import static com.chandler.config.server.entity.value.RuleConstant.*;


/**
 *
 * @author 钱丁君-chandler 2021/3/15 11:44 AM
 * @version 1.0.0
 * @since 1.8
 */
public class StringHandler implements Handler {
    private static String equals = "=";
    private static String in = "@";
    private static String sub = "sub";

    @Override
    public String handle(String wild, RulePatternBo pattern) {
        wild = wild.replace("'", "");
        if (wild.indexOf(equals) != -1
                && wild.indexOf(OperationType.equal.getOperation()) == -1
                && wild.indexOf(OperationType.no_equal.getOperation()) == -1) {
            wild = wild.replace(equals, OperationType.equal.getOperation());
        }

        if (wild.indexOf(in) != -1) {
            wild = wild.replace(in, OperationType.in.getOperation());
        }

        split(wild, OperationType.in, pattern);
        split(wild, OperationType.equal, pattern);
        split(wild, OperationType.no_equal, pattern);
        split(wild, OperationType.greater, pattern);
        split(wild, OperationType.less, pattern);
        //函数处理
        String param = pattern.getParam().getAbbreviation();
        if (param.indexOf(sub) != -1) {
            //分隔出函数
            String[] params = param.split("\\.");
            pattern.getParam().setAbbreviation(params[ZERO]);
            pattern.setFunction(RuleFunctionBo.builder().functionKey(SUBSTRING).build());

            pattern.setFunctionParam(params[ONE].substring(params[ONE].indexOf('(') + 1, params[ONE].indexOf(')')));
        }
        pattern.getParam().setType(DataType.STRING);

        //in 处理
        if (OperationType.in.getOperation().equalsIgnoreCase(pattern.getOperation().getOperation())) {
            String value = pattern.getValue();
            value = value.replace("(", "").replace(")", "");
            value = value.replace("，",",");
            pattern.setValue(value);
        }

        return wild;
    }

    @Override
    public String rollback(RulePatternBo pattern) {
        StringBuilder builder = new StringBuilder();
        String param = pattern.getParam().getAbbreviation();
        if (!param.equals(o) && !param.equals(c) && !param.equals(u) && !param.equals(rc)) {
            return builder.toString();
        }
        builder.append(param);
        //substring
        if (!Objects.isNull(pattern.getFunction()) && SUBSTRING.equalsIgnoreCase(pattern.getFunction().getFunctionKey())) {
            builder.append(".").append(sub).append("(").append(pattern.getFunctionParam()).append(")");
        }

        String operation = pattern.getOperation().getOperation();
        if (operation.equalsIgnoreCase(OperationType.equal.getOperation())) {
            operation = equals;
        }
        String value = pattern.getValue();
        if (value.indexOf('\"') != -1) {
            value = value.replace("\"", "");
        }
        if (operation.equalsIgnoreCase(OperationType.in.getOperation())) {
            operation = in;
            if (!value.startsWith("(")) {
                value = "(" + value;
            }
            if (!value.endsWith(")")) {
                value = value + ")";
            }
        }
        builder.append(operation);
        builder.append(value);
        return builder.toString();
    }

    @Override
    public Boolean match(String param) {
        return !new NumberHandler().match(param) && !new TimeHandler().match(param);
    }

}
