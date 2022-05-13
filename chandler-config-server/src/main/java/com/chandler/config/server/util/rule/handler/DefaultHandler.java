/*
 * configservice-parent
 * 2021/3/18 6:20 PM
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

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

import com.chandler.config.server.entity.bo.rule.RulePatternBo;

import static com.chandler.config.server.entity.value.RuleConstant.*;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/18 6:20 PM
 * @version 1.0.0
 * @since 1.8
 */
public class DefaultHandler implements Handler {
    List<Handler> handlers = Lists.newArrayList();

    public DefaultHandler(){
        handlers.add(new NumberHandler());
        handlers.add(new StringHandler());
        handlers.add(new TimeHandler());
    }

    @Override
    public String handle(String wild, RulePatternBo pattern) {
        Optional<Handler> handler = handlers.stream().filter(h -> h.match(wild)).findFirst();
        if (handler.isPresent()) {
            return handler.get().handle(wild, pattern);
        }
        return null;
    }

    @Override
    public String rollback(RulePatternBo pattern) {
        String param = pattern.getParam().getAbbreviation();
        //p_v，extendedVersion，在旧体系使用e，为了缩写的统一性，修改为v;v->e
        if (param.equals(version)) {
            param = extendedVersion;
        }
        String finalParam = param;
        Optional<Handler> handler = handlers.stream().filter(h -> h.match(finalParam)).findFirst();
        if (handler.isPresent()) {
            return handler.get().rollback(pattern);
        }
        return null;
    }

    @Override
    public Boolean match(String param) {
        return false;
    }
}
