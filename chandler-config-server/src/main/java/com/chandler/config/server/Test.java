package com.chandler.config.server;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author 钱丁君-chandler 2021/12/20 1:43 下午
 * @since 1.8
 */
public class Test {
    public static void main(String[] args) {
        List<RuleFilter> filters= Lists.newArrayList();
        Map<String,String> components=Maps.newHashMap();
        components.put("bizconfig-starter","V.1.0.7");
        filters.add(RuleFilter.builder()
                        .filterName("long_uid")
                        .type("PARAMETER")
                        .components(components)
                .build());
        System.out.println(JSON.toJSONString(filters));
    }

    @Data
    @Builder
    static class RuleFilter{
        String filterName;
        String type;
        Map<String,String> components;
    }
}
