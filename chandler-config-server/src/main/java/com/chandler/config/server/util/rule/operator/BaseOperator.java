package com.chandler.config.server.util.rule.operator;

import com.ql.util.express.Operator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Slf4j
abstract class BaseOperator extends Operator {
    private static final long serialVersionUID = 1;
    public abstract String name();

    public abstract Boolean execute(Object[] list);

    @Override
    public Object executeInner(Object[] list) throws Exception {
        return execute(list);
    }

    /**
     * 校验参数是否合法
     *
     * @param list 传入参数
     * @return true：不合法 false：合法
     */
    protected boolean isInvalid(Object[] list) {
        return list.length != 2
                || ObjectUtils.isEmpty(list[0])
                || ObjectUtils.isEmpty(list[1]);
    }

}
