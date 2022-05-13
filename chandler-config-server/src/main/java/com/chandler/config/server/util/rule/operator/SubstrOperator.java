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
import org.apache.commons.lang3.StringUtils;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/19 3:13 PM
 * @version 1.0.0
 * @since 1.8
 */
public class SubstrOperator extends OperatorBase {
    private static final long serialVersionUID = 1;

    public SubstrOperator() {
        this.name = "substr";
        this.aliasName = "截取";
    }

    @Override
    public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
        String param = list.get(0).toString();
        String data = String.valueOf(parent.getParent().get(param));
        if(StringUtils.isEmpty(data)){
            return new OperateData("", String.class);
        }
        Integer start = Integer.valueOf(list.get(1).toString());
        if (list.length > 2) {
            Integer end = Integer.valueOf(list.get(2).toString());
            return new OperateData(data.substring(start, end), String.class);
        } else {
            return new OperateData(data.substring(start), String.class);
        }
    }
}
