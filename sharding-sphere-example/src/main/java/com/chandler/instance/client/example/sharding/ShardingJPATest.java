/*
 * sharding-sphere-example
 * 2021/9/28 4:27 下午
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.instance.client.example.sharding;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/9/28 4:27 下午
 * @version 1.0.0
 * @since 1.8
 */
public class ShardingJPATest {
    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        MysqlDataSource dataSource1 = new MysqlDataSource();
        dataSource1.setURL("jdbc:mysql://localhost:3306/db0");
        dataSource1.setUser("micro");
        dataSource1.setPassword("123456");
        dataSourceMap.put("db0", dataSource1);

        // 配置第 2 个数据源
        MysqlDataSource dataSource2 = new MysqlDataSource();
        dataSource2.setURL("jdbc:mysql://122.152.219.156:3306/db1");
//        dataSource2.setURL("jdbc:mysql://localhost:3306/db1");
        dataSource2.setUser("micro");
        dataSource2.setPassword("123456");
        dataSourceMap.put("db1", dataSource2);

        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "db${0..1}.t_order_${0..1}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "db${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());

//        String sql = "SELECT * FROM t_order";
//        try (
//                Connection conn = dataSource.getConnection();
//                PreparedStatement ps = conn.prepareStatement(sql)) {
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    int orderId = rs.getInt("order_id");
//                    int userId = rs.getInt("user_id");
//                    System.out.println("orderId:" + orderId + ",userId：" + userId);
//                }
//            }
//        }

        String sql1 = "insert into t_order (order_id,user_id) values (?,?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql1)){
            ps.setInt(1,7);
            ps.setInt(2,7);
            System.out.println(ps.execute());
        }
    }
}
