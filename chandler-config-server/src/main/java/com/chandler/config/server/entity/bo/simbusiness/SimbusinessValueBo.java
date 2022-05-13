/*
 * configservice-parent
 * 2021/3/23 2:36 PM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.bo.simbusiness;

import com.chandler.config.server.entity.value.Status;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/23 2:36 PM
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class SimbusinessValueBo {
    private String config;
    private Integer version;
    private List<SimbusinessClusterValueBo> values;
    private Boolean display;
    private Boolean defStatus;
    private String note;
    private Status status;
}
