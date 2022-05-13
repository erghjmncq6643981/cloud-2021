/*
 * kyle-rabbitmq
 * 2021/3/15 11:43 AM
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

import com.chandler.config.server.entity.bo.rule.RuleOperationBo;
import com.chandler.config.server.entity.bo.rule.RuleParamBo;
import com.chandler.config.server.entity.bo.rule.RulePatternBo;
import com.chandler.config.server.entity.value.DataType;
import com.chandler.config.server.entity.value.OperationType;

import static com.chandler.config.server.entity.value.RuleConstant.*;


/**
 * Description: 数值类型
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/15 11:43 AM
 * @version 1.0.0
 * @since 1.8
 */
public class NumberHandler implements Handler {
    private static String equals = "=";
    private static String in = "@";

    @Override
    public String handle(String wild, RulePatternBo pattern) {
        if (wild.indexOf(equals) != -1
                && wild.indexOf(OperationType.equal.getOperation()) == -1
                && wild.indexOf(OperationType.no_equal.getOperation()) == -1) {
            wild = wild.replace(equals, OperationType.equal.getOperation());
        }

        if (wild.indexOf(in) != -1) {
            wild = wild.replace(in, OperationType.in.getOperation());
        }
        split(wild, OperationType.greater, pattern);
        split(wild, OperationType.less, pattern);
        split(wild, OperationType.equal, pattern);
        split(wild, OperationType.no_equal, pattern);
        split(wild, OperationType.greater_equal, pattern);
        split(wild, OperationType.less_equal, pattern);
        split(wild, OperationType.in, pattern);

        //p_v，extendedVersion，在旧体系使用e，为了缩写的统一性，修改为v;e->ev
        if (pattern.getParam().getAbbreviation().equals(extendedVersion)) {
            pattern.getParam().setAbbreviation(version);
        }

        pattern.getParam().setType(DataType.INTEGER);

        return wild;
    }

    @Override
    public String rollback(RulePatternBo pattern) {
        String param = pattern.getParam().getAbbreviation();
        //p_v，extendedVersion，在旧体系使用e，为了缩写的统一性，修改为v;v->e
        if (param.equals(version)) {
            param = extendedVersion;
        }

        String operation = pattern.getOperation().getOperation();
        if (operation.equalsIgnoreCase(OperationType.equal.getOperation())) {
            operation = equals;
        }
        String value = pattern.getValue();
        if (operation.equalsIgnoreCase(OperationType.in.getOperation())) {
            operation = in;
            if (!value.startsWith("(")) {
                value = "(" + value;
            }
            if (!value.endsWith(")")) {
                value = value + ")";
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(param);
        builder.append(operation);
        builder.append(value);
        return builder.toString();
    }

    @Override
    public Boolean match(String param) {
        return param.startsWith(internalVersion) ||
                param.startsWith(dv) ||
                param.startsWith(av) ||
                param.startsWith(bv) ||
                param.startsWith(extendedVersion);
    }
}
