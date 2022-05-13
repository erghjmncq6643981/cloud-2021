/*
 * kyle-rabbitmq
 * 2021/3/15 11:42 AM
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
import com.chandler.config.server.entity.value.OperationType;

import static com.chandler.config.server.entity.value.RuleConstant.*;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/15 11:42 AM
 * @version 1.0.0
 * @since 1.8
 */
public interface Handler {
    String handle(String wild, RulePatternBo pattern);

    String rollback(RulePatternBo pattern);

    Boolean match(String param);

    default void split(String data, OperationType operation, RulePatternBo pattern) {
        String o = operation.getOperation();
        if (data.indexOf(o) != -1) {
            String[] strs = data.split(operation.getOperation());
            pattern.setOperation(RuleOperationBo.builder().operation(o).build());
            pattern.setParam(RuleParamBo.builder().abbreviation(strs[ZERO]).build());
            pattern.setValue(strs[ONE]);
        }
    }
}
