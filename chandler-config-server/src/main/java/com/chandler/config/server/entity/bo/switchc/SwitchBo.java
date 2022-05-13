/*
 * configservice-parent
 * 2021/3/23 11:26 AM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.bo.switchc;

import com.chandler.config.server.entity.bo.rule.RuleBo;
import com.chandler.config.server.entity.value.Status;
import com.chandler.config.server.entity.value.SwitchValueType;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/23 11:26 AM
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class SwitchBo {
    private String configName;
    private String config;
    private Set<String> application;
    private String ownerApplication;
    private SwitchValueType type;
    private String dutyDepart;
    private String dutyDepartCode;
    private String dutyPerson;
    private String dutyPersonUid;
    private Boolean dynamic;
    private Boolean consistent;
    private List<SwitchValueBo> values;
    private List<RuleBo> rules;
    //版本号
    private Integer version;
    private String note;
    private Status status;
    private String operator;
    private Timestamp updatedAt;

}
