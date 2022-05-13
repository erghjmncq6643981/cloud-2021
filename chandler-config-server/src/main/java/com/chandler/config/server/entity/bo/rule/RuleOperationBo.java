/*
 * configservice-parent
 * 2021/3/22 2:00 PM
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

import com.chandler.config.server.entity.value.DataType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/22 2:00 PM
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class RuleOperationBo {
    private String operation;
    private String operatorName;
    /**
     * 作用类型：STRING->S、INTEGER->I、TIME->T；支持多种通过 - 链接，比如支持STRING和INTEGER，就是S-I
     */
    private List<DataType> type;
    private String operator;
}
