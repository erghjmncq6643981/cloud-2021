/*
 * kyle-rabbitmq
 * 2021/1/15 2:02 PM
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

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/1/15 2:02 PM
 * @version 1.0.0
 * @since 1.8
 */
public enum OperationType {
    like("like", "匹配"),
    in("in", "存在于"),
    greater(">", "大于"),
    equal("==", "等于"),
    no_equal("!=", "不等于"),
    less("<", "小于"),
    greater_equal(">=", "大于等于"),
    less_equal("<=", "小于等于");

    private String operation;
    private String chinese;

    OperationType(String operation, String chinese) {
        this.operation = operation;
        this.chinese = chinese;
    }

    public String getOperation() {
        return operation;
    }

    public String getChinese() {
        return chinese;
    }

    public static OperationType valueOfString(String operation) {
        Optional<OperationType> type = Stream.of(OperationType.values()).filter(o -> o.getOperation().equals(operation)).findFirst();
        if (type.isPresent()) {
            return type.get();
        }
        return null;
    }
}
