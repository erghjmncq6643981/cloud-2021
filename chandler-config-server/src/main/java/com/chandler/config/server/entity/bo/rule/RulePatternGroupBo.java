/*
 * config-starter-core
 * 2021/6/4 9:58 上午
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.bo.rule;

import com.chandler.config.server.entity.value.ConnectorType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/6/4 9:58 上午
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class RulePatternGroupBo {
    private Integer sort;
    private List<RulePatternBo> patterns;
    private ConnectorType connector;
}
