/*
 * confplus-starter
 * 2021/6/8 11:40 上午
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.bo.simbusiness.vo;

import com.chandler.config.server.entity.Constant;
import com.chandler.config.server.entity.bo.simbusiness.SimbusinessBo;
import com.chandler.config.server.entity.bo.simbusiness.SimbusinessClusterValueBo;
import com.chandler.config.server.entity.bo.simbusiness.SimbusinessValueBo;
import com.chandler.config.server.entity.value.OperationStatus;
import com.chandler.config.server.entity.value.Status;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/6/8 11:40 上午
 * @version 1.0.0
 * @since 1.8
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MigratoryConfigVo {
    private Set<String> apps;
    private String name;
    private String application;
    private String relateCreatedBy;
    private String ownerApplication;
    private String source;
    private String rule;
    private String note;
    private Status status;
    private String updatedBy;
    private List<MigratoryConfigValueVo> values;
    private OperationStatus operation;

    public SimbusinessBo voToBo() {
        SimbusinessBo bo = SimbusinessBo.builder()
                .config(name)
                .configName(name)
                .ownerApplication(ownerApplication)
                .dutyPerson("")
                .dutyPersonUid("")
                .dutyDepart("")
                .dutyDepartCode("")
                .source(!Constant.CONFPLUS.equalsIgnoreCase(source))
                .note(note)
                .operator(updatedBy)
                .status(Objects.isNull(status) ? Status.ENABLED.name() : status.toString())
                .version(1)
                .build();

        if (!CollectionUtils.isEmpty(values)) {
            bo.setType(values.get(0).getType());
            bo.setValueBos(values.stream().collect(Collectors.groupingBy(MigratoryConfigValueVo::getVersion))
                    .entrySet().stream()
                    .map(e -> {
                        SimbusinessValueBo value = e.getValue().get(0).voToBo();
                        value.setValues(e.getValue().stream()
                                .map(v-> SimbusinessClusterValueBo.builder()
                                        .clusterName(v.getClusterName())
                                        .value(v.getValue())
                                        .build())
                                .collect(Collectors.toList()));
                        return value;
                    }).collect(Collectors.toList()));
        }
        return bo;
    }
}
