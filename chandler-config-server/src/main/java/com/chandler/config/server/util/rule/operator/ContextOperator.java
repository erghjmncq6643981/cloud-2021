/*
 * configservice-parent
 * 2021/3/19 3:12 PM
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

import com.ql.util.express.ArraySwap;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.instruction.op.OperatorBase;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/19 3:12 PM
 * @version 1.0.0
 * @since 1.8
 */
public class ContextOperator extends OperatorBase {
    private static final long serialVersionUID = 1;
    public ContextOperator() {
        this.name = "context";
        this.aliasName = "上下文";
    }

    @Override
    public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
        String key = list.get(0).toString();
        return new OperateData(parent.get(key), String.class);
    }
}
