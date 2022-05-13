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


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chandler.config.server.entity.value.ConnectorType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RulePatternBo {
    private String ruleKey;
    /**
     * 入参
     */
    @JSONField(serialzeFeatures = {SerializerFeature.DisableCircularReferenceDetect})
    private RuleParamBo param;
    /**
     * 操作符
     */
    @JSONField(serialzeFeatures = {SerializerFeature.DisableCircularReferenceDetect})
    private RuleOperationBo operation;
    private String value;
    /**
     * 函数
     */
    @JSONField(serialzeFeatures = {SerializerFeature.DisableCircularReferenceDetect})
    private RuleFunctionBo function;
    private String functionParam;
    /**
     * 连接符，&&，||, !
     */
    private ConnectorType connector;
    private Integer num;
    private Integer sort;
    private String operator;
}
