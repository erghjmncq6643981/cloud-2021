package com.chandler.config.server.entity.bo.simbusiness.vo;

import lombok.*;

import java.util.List;

/**
 * @Description
 * @Author wangqikang
 * @Date 2019-07-24 09:44
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfVo {
    private String name;
    private String ownerApplication;
    private String rule;
    private String status;
    private String note;

    private List<ConfVersionVo> confVersionVos;
}
