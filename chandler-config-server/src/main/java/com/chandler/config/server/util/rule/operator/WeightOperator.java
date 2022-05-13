/*
 * configservice-parent
 * 2021/3/19 3:13 PM
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

import java.util.Random;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/19 3:13 PM
 * @version 1.0.0
 * @since 1.8
 */
public class WeightOperator extends OperatorBase {
    private static final long serialVersionUID = 1;
    private Random random = new Random();

    public WeightOperator() {
        this.name = "weight";
        this.aliasName = "权重";
    }

    @Override
    public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
        return new OperateData(random.nextInt(100), Integer.class);
    }
}
