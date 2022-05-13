/*
 * confplus
 * 2020/6/15 11:41 AM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.util.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chandler.config.server.entity.Constant;
import com.chandler.config.server.entity.bo.rule.RuleBo;
import com.chandler.config.server.entity.bo.rule.RulePatternBo;
import com.chandler.config.server.entity.bo.rule.RulePatternGroupBo;
import com.chandler.config.server.entity.value.ConnectorType;
import com.chandler.config.server.entity.value.DataType;
import com.chandler.config.server.entity.value.OperationType;
import com.chandler.config.server.entity.value.Status;
import com.chandler.config.server.exception.RuleException;
import com.chandler.config.server.util.rule.handler.DefaultHandler;
import com.chandler.config.server.util.rule.handler.Handler;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.chandler.config.server.entity.value.RuleConstant.*;


/**
 * Description: 规则转化工具
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/18 3:44 PM
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
public class RuleUtil {

    /**
     * 旧规则转化成新规则对象集合
     * <p>
     * [oldRule]
     *
     * @return java.util.List<cn.caijiajia.confplus.common.promax.bo.ConfigRuleVo>
     * @Autnor chandler
     * @create 2020/8/21 4:50 PM
     */
    public Map<String, RuleBo> rebirth(String oldRule) {
        List<String> ruleStrs = JSON.parseObject(oldRule.trim().replace(" ", ""), new TypeReference<List<String>>() {
        });

        Map<String, RuleBo> ruleMap = new LinkedHashMap<>();
        Integer sort = 1;
        for (String r : ruleStrs) {
            String ruleKey = RandomStringUtils.randomAlphanumeric(20);
            RuleBo rule = RuleBo.builder()
                    .ruleKey(ruleKey)
                    .wildRule(r)
                    .groups(Lists.newArrayList())
                    .sort(sort)
                    .status(Status.ENABLED)
                    .build();
            sort++;
            int firstSplit = r.indexOf(':');
            String[] strs = new String[]{r.substring(0, firstSplit), r.substring(firstSplit + 1)};
            if (StringUtils.isNoneBlank(strs)) {
                String wild = strs[ZERO];
                String version = strs[ONE];
                //判断匹配的对象是否为数字
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher isNum = pattern.matcher(version);
                if (isNum.matches()) {
                    Integer versionNum = Integer.valueOf(version);
                    rule.setVersion(versionNum);
                    if (wild.equals(Constant.DEFAULT)) {
                        ruleMap.put(ruleKey, rule);
                    } else {
                        try {
                            rule.getGroups().add(RulePatternGroupBo.builder()
                                    .sort(1)
                                    .patterns(wildToNew(wild))
                                    .connector(ConnectorType.AND)
                                    .build());
                        } catch (Exception e) {
                            throw new RuleException(1002, String.format("%s规则转换时发生异常！异常信息为：%s",
                                    oldRule, e));
                        }
                        ruleMap.put(ruleKey, rule);
                    }
                } else {
                    throw new RuleException(1002, String.format("%s规则转换时发生异常，版本：%s无法转化数值类型！",
                            oldRule, version));
                }

            }
        }
        return ruleMap;
    }

    private List<RulePatternBo> wildToNew(String wild) {
        if (wild.indexOf(or) != -1 && wild.indexOf(ConnectorType.OR.getConnector()) == -1) {
            wild = wild.replace(or, ConnectorType.OR.getConnector());
        }
        if (wild.indexOf(and) != -1 && wild.indexOf(ConnectorType.AND.getConnector()) == -1) {
            wild = wild.replace(and, ConnectorType.AND.getConnector());
        }
        List<String[]> box = new ArrayList<>();
        slip(box, EMPTY, wild);
        List<RulePatternBo> patterns = new ArrayList<>();
        int i = 1;
        for (String[] ball : box) {
            Handler handler = new DefaultHandler();
            RulePatternBo pattern = RulePatternBo.builder()
                    .connector(ConnectorType.valueOfConnector(ball[ZERO]))
                    .num(1)
                    .sort(i)
                    .build();
            handler.handle(ball[ONE], pattern);
            patterns.add(pattern);
            i++;
        }
        return patterns;
    }

    /**
     * u.sub(0,1)@(2) && t > 2020-03-30-17-00-00 && rc = 'hb' & i > 1054999
     *
     * @param box
     * @param connector
     * @param wild
     */
    private void slip(List<String[]> box, String connector, String wild) {
        if (wild.indexOf(ConnectorType.AND.getConnector()) != -1) {
            int firstSplit = wild.indexOf(ConnectorType.AND.getConnector());
            slip(box, connector, wild.substring(0, firstSplit));
            slip(box, ConnectorType.AND.getConnector(), wild.substring(firstSplit + 2));
        } else if (wild.indexOf(ConnectorType.OR.getConnector()) != -1) {
            int firstSplit = wild.indexOf(ConnectorType.OR.getConnector());
            slip(box, connector, wild.substring(0, firstSplit));
            slip(box, ConnectorType.OR.getConnector(), wild.substring(firstSplit + 2));
        } else {
            box.add(new String[]{connector, wild});
        }
    }

    /**
     * 新规则对象转化成旧规则
     * [rule]
     *
     * @return java.lang.String
     * @Autnor chandler
     * @create 2021/3/19 2:32 PM
     */
    public String rollback(List<RuleBo> rules) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (!CollectionUtils.isEmpty(rules)) {
            rules.stream().filter(r -> !CollectionUtils.isEmpty(r.getGroups()))
                    .sorted(Comparator.comparing(RuleBo::getSort))
                    .forEach(r -> {
                        builder.append("\"");

                        Handler handler = new DefaultHandler();
                        if (!CollectionUtils.isEmpty(r.getGroups().get(0).getPatterns())) {
                            String patterns = r.getGroups().get(0).getPatterns().stream().map(p -> {
                                StringBuilder pattern = new StringBuilder();
                                if (p.getConnector().equals(ConnectorType.AND)) {
                                    pattern.append(and);
                                } else if (p.getConnector().equals(ConnectorType.OR)) {
                                    pattern.append(or);
                                }
                                String block = handler.rollback(p);
                                pattern.append(handler.rollback(p));
                                //pattern无法转化成旧规则时，则会抛出异常
                                if (StringUtils.isEmpty(block) ||
                                        (!Objects.isNull(p.getFunction()) && !p.getFunction().getFunctionKey().equals(SUBSTRING))) {
                                    throw new RuleException(1001, String.format("%s规则rollback时发生异常，无法转化成旧！",
                                            p.toString()));
                                }
                                return pattern.toString();
                            }).collect(Collectors.joining());
                            builder.append(patterns);
                        }

                        builder.append(":");
                        builder.append(r.getVersion());
                        builder.append("\"");
                        builder.append(",");
                    });
        }
        //default
        Optional<RuleBo> defBo = rules.stream().filter(r -> CollectionUtils.isEmpty(r.getGroups()) ||
                CollectionUtils.isEmpty(r.getGroups().get(0).getPatterns())).findFirst();
        if (defBo.isPresent()) {
            builder.append("\"");
            builder.append(Constant.DEFAULT);
            builder.append(":");
            builder.append(defBo.get().getVersion());
            builder.append("\"");
        }

        builder.append("]");
        return builder.toString().replace("\"&", "\"").replace("\"|", "\"");
    }

    public static Set<String> findCustomParam(RuleBo rule) {
        Optional<List<RulePatternBo>> optional = rule.getGroups().stream()
                .map(RulePatternGroupBo::getPatterns)
                .reduce((ps1, ps2) -> {
                    ps1.addAll(ps2);
                    return ps1;
                });
        if (!optional.isPresent()) {
            return Sets.newHashSet();
        }
        Optional<RulePatternBo> custom = optional.get().stream()
                .filter(p -> !Objects.isNull(p.getFunction()) && CONTEXT.equals(p.getParam().getParam())).findFirst();
        if (!custom.isPresent()) {
            return Sets.newHashSet();
        }
        return optional.get().stream()
                .filter(p -> !Objects.isNull(p.getFunction()) && CONTEXT.equals(p.getParam().getParam()))
                .map(RulePatternBo::getFunctionParam).filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
    }

    public static String getScript(RuleBo rule) {
        if (Objects.isNull(rule) || CollectionUtils.isEmpty(rule.getGroups())) {
            return null;
        }
        if (rule.getGroups().size() == 1) {
            List<RulePatternBo> patterns = rule.getGroups().get(0).getPatterns();
            patterns.sort(Comparator.comparing(RulePatternBo::getSort));
            return getScript(patterns);
        }

        rule.getGroups().sort(Comparator.comparing(RulePatternGroupBo::getSort));
        String template = rule.getTemplate();
        template = template.substring(template.indexOf(ConnectorType.AND.getConnector()) + 2);
        String[] datas = new String[rule.getGroups().size()];
        int i = 0;
        for (RulePatternGroupBo group : rule.getGroups()) {
            List<RulePatternBo> ps = group.getPatterns();
            ps.sort(Comparator.comparing(RulePatternBo::getSort));
            datas[i] = getScript(ps);
            i++;
        }
        return String.format(template, datas);
    }

    private static String getScript(List<RulePatternBo> patterns) {
        if (CollectionUtils.isEmpty(patterns)) {
            return null;
        }
        String wild = patterns.stream().map(p -> {
            StringBuilder pattern = new StringBuilder();
            pattern.append(p.getConnector().getConnector());
            pattern.append("(");
            String functionParam = p.getFunctionParam();
            String param = p.getParam().getParam();
            if (param.equals(WEIGHT)) {
                param = param + "(" + ")";
            }

            if (Objects.isNull(p.getFunction()) || Objects.isNull(p.getFunction().getFunctionKey())) {
                functionParam = EMPTY;
                //param
                pattern.append(param);
            } else if (p.getFunction().getFunctionKey().equals(SUBSTRING)) {
                functionParam = String.format(p.getFunction().getTemplate(), param, functionParam);
            } else if (p.getFunction().getFunctionKey().equals(GET)) {
                //param
                pattern.append(param);
                functionParam = String.format(p.getFunction().getTemplate(), functionParam);
            }
            pattern.append(functionParam);
            // operation
            String operation = p.getOperation().getOperation();
            pattern.append(" ");
            pattern.append(operation);
            pattern.append(" ");
            String value = p.getValue().trim();

            if (DataType.STRING.equals(p.getParam().getType()) || DataType.TIME.equals(p.getParam().getType())) {
                if (operation.equals(OperationType.in.name()) || operation.equals(inPeriod)) {
                    String[] strs = new String[1];
                    if (value.indexOf(",") != -1) {
                        strs = p.getValue().split(",");
                    } else {
                        strs[0] = p.getValue();
                    }
                    value = Stream.of(strs).filter(StringUtils::isNotEmpty).map(String::trim).map(v -> {
                        if (!v.startsWith("'")) {
                            v = "'" + v;
                        }
                        if (!v.endsWith("'")) {
                            v = v + "'";
                        }
                        return v;
                    }).reduce((v1, v2) -> v1 + "," + v2).get();


                    if (!value.startsWith("(")) {
                        value = "(" + value;
                    }
                    if (!value.endsWith(")")) {
                        value = value + ")";
                    }
                } else {
                    value = "'" + value + "'";
                }
            }

            //value
            pattern.append(value);
            pattern.append(")");
            return pattern.toString();
        }).collect(Collectors.joining());
        StringBuilder pattern = new StringBuilder();
        pattern.append("(");
        pattern.append(wild);
        pattern.append(")");
        return pattern.toString().replace("(&&", "(").replace("(||", "(");
    }

    public static String getBusinessScript(RuleBo rule) {
        if (CollectionUtils.isEmpty(rule.getGroups())) {
            return null;
        }
        if (rule.getGroups().size() == 1) {
            List<RulePatternBo> patterns = rule.getGroups().get(0).getPatterns();
            patterns.sort(Comparator.comparing(RulePatternBo::getSort));
            return getBusinessScript(patterns);
        }

        rule.getGroups().sort(Comparator.comparing(RulePatternGroupBo::getSort));
        String template = rule.getTemplate();
        template = template.substring(template.indexOf(ConnectorType.AND.getConnector()) + 2);
        String[] datas = new String[rule.getGroups().size()];
        int i = 0;
        for (RulePatternGroupBo group : rule.getGroups()) {
            List<RulePatternBo> ps = group.getPatterns();
            ps.sort(Comparator.comparing(RulePatternBo::getSort));
            datas[i] = getBusinessScript(ps);
            i++;
        }
        return String.format(template, datas);
    }

    private static String getBusinessScript(List<RulePatternBo> patterns) {
        if (CollectionUtils.isEmpty(patterns)) {
            return null;
        }
        String wild = patterns.stream().map(p -> {
            StringBuilder pattern = new StringBuilder();
            pattern.append(p.getConnector().getConnector());
            pattern.append("(");
            String functionParam = p.getFunctionParam();
            String param = p.getParam().getParam();
            if (param.equals(WEIGHT)) {
                param = param + "(" + ")";
            }
            if (Objects.isNull(p.getFunction()) || Objects.isNull(p.getFunction().getFunctionKey())) {
                functionParam = EMPTY;
                //param
                pattern.append(param);
            } else if (p.getFunction().getFunctionKey().equals(SUBSTRING)) {
                functionParam = String.format(p.getFunction().getTemplate(), param, functionParam);
            } else if (p.getFunction().getFunctionKey().equals(GET)) {
                //param
                pattern.append(param);
                functionParam = String.format(p.getFunction().getTemplate(), functionParam);
            }
            pattern.append(functionParam);
            // operation
            pattern.append(" ");
            pattern.append(p.getOperation().getOperatorName());
            pattern.append(" ");
            //value
            //补偿
            String value = p.getValue().trim();
            if (OperationType.in.getOperation().equals(p.getOperation().getOperation()) ||
                    p.getOperation().getOperation().equals(inPeriod)) {
                if (!value.startsWith("(")) {
                    value = "(" + value;
                }
                if (!value.endsWith(")")) {
                    value = value + ")";
                }
            }
            pattern.append(value);
            pattern.append(")");
            return pattern.toString();
        }).collect(Collectors.joining());
        StringBuilder pattern = new StringBuilder();
        pattern.append("(");
        pattern.append(wild);
        pattern.append(")");
        return pattern.toString().replace("(&&", "(").replace("(||", "(");
    }

    public static String getOldScript(RuleBo rule) {
        if (CollectionUtils.isEmpty(rule.getGroups())) {
            return null;
        }
        return rule.getGroups().get(0).getPatterns().stream().map(p -> {
            StringBuilder pattern = new StringBuilder();
            pattern.append(p.getConnector().getConnector());
            pattern.append("(");
            pattern.append(p.getParam().getAbbreviation());
            String functionParam = p.getFunctionParam();

            if (Objects.isNull(p.getFunction()) || Objects.isNull(p.getFunction().getFunctionKey())) {
                functionParam = EMPTY;
                //param
            } else if (p.getFunction().getFunctionKey().equals(SUBSTRING)) {
                functionParam = "." + "substring" + "(" + functionParam + ")";
            }
            pattern.append(functionParam);
            pattern.append(" ");
            String operation = p.getOperation().getOperation();
            pattern.append(operation);
            pattern.append(" ");
            //value
            //补偿
            String value = p.getValue().trim();
            if (DataType.STRING.equals(p.getParam().getType()) || DataType.TIME.equals(p.getParam().getType())) {
                if (operation.equals(OperationType.in.name()) || operation.equals(inPeriod)) {
                    String[] strs = new String[1];
                    if (value.indexOf(",") != -1) {
                        strs = p.getValue().split(",");
                    } else {
                        strs[0] = p.getValue();
                    }
                    value = Stream.of(strs).filter(StringUtils::isNotEmpty).map(String::trim).map(v -> {
                        if (!v.startsWith("'")) {
                            v = "'" + v;
                        }
                        if (!v.endsWith("'")) {
                            v = v + "'";
                        }
                        return v;
                    }).reduce((v1, v2) -> v1 + "," + v2).get();


                    if (!value.startsWith("(")) {
                        value = "(" + value;
                    }
                    if (!value.endsWith(")")) {
                        value = value + ")";
                    }
                } else {
                    value = "'" + value + "'";
                }
            }
            pattern.append(value);
            pattern.append(")");
            return pattern.toString();
        }).collect(Collectors.joining());
    }

    public static String getScriptExcludeContext(RuleBo rule) {
        if (CollectionUtils.isEmpty(rule.getGroups())) {
            return null;
        }
        if (rule.getGroups().size() == 1) {
            List<RulePatternBo> patterns = rule.getGroups().get(0).getPatterns().stream()
                    .filter(p -> !CONTEXT.equals(p.getParam().getParam()))//过滤上下文
                    .collect(Collectors.toList());
            patterns.sort(Comparator.comparing(RulePatternBo::getSort));
            return getScript(patterns);
        }

        rule.getGroups().sort(Comparator.comparing(RulePatternGroupBo::getSort));
        String template = rule.getTemplate();
        template = template.substring(template.indexOf(ConnectorType.AND.getConnector()) + 2);
        String[] datas = new String[rule.getGroups().size()];
        int i = 0;
        for (RulePatternGroupBo group : rule.getGroups()) {
            List<RulePatternBo> ps = group.getPatterns().stream()
                    .filter(p -> !CONTEXT.equals(p.getParam().getParam()))//过滤上下文
                    .collect(Collectors.toList());
            ps.sort(Comparator.comparing(RulePatternBo::getSort));
            StringBuilder pattern = new StringBuilder();
            pattern.append("(");
            pattern.append(getScript(ps));
            pattern.append(")");
            datas[i] = pattern.toString().replace("(&&", "(").replace("(||", "(");
            i++;
        }
        return String.format(template, datas);
    }
}

