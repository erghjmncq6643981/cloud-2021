/*
 * confplus-starter
 * 2021/6/8 4:17 下午
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

import com.chandler.config.server.entity.bo.simbusiness.SimbusinessValueBo;
import com.chandler.config.server.entity.value.SimbusinessValueType;
import com.chandler.config.server.entity.value.Status;
import lombok.*;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/6/8 4:17 下午
 * @version 1.0.0
 * @since 1.8
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MigratoryConfigValueVo {
    private Integer version;
    private Boolean display;
    private SimbusinessValueType type;
    private String clusterName;
    private String value;
    private String versionNote;
    private Status versionStatus;

    public SimbusinessValueBo voToBo(){
        return SimbusinessValueBo.builder()
                .version(version)
                .display(display)
                .note(versionNote)
                .status(versionStatus)
                .build();
    }
}
