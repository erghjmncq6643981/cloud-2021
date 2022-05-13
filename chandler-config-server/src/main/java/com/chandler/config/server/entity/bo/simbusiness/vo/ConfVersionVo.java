package com.chandler.config.server.entity.bo.simbusiness.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author wangqikang
 * @Date 2019-07-24 09:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfVersionVo {
    private String name;
    private Integer version;
    private String type;
    private String value;
    private String status;
    private String note;

    private List<ConfVersionClusterVo> confVersionClusterVos;
}
