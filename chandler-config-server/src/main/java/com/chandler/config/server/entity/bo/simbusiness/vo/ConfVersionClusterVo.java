package com.chandler.config.server.entity.bo.simbusiness.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author wangqikang
 * @Date 2019-07-24 09:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfVersionClusterVo {
    private String clusterName;
    private String clusterValue;
}
