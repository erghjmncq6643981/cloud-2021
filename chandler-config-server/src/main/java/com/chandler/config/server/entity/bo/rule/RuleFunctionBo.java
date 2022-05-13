/*
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

/**
 *
 * @author 钱丁君-chandler 2021/3/22 2:10 PM
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class RuleFunctionBo {
    private String functionKey;
    private String name;
    private String template;
    private DataType type;
    private String operator;
}
