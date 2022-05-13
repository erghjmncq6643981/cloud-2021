/*
 * config-starter-core
 * 2021/6/29 5:23 下午
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

import lombok.Builder;
import lombok.Data;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/6/29 5:23 下午
 * @version 1.0.0
 * @since 1.8
 */
@Data
@Builder
public class SimbusinessClusterValueBo {
    private String clusterName;
    private String value;
}
