/*
 * sharding-sphere-example
 * 2021/9/29 2:21 下午
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

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/9/29 2:21 下午
 * @version 1.0.0
 * @since 1.8
 */
public class ShardingYamlTest {
    public static void main(String[] args) {
        // 创建 ShardingSphereDataSource
        try {
            File file = ResourceUtils.getFile("classpath:sharding.yaml");
            DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(file);
            String sql = "SELECT * FROM t_order";
            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int orderId = rs.getInt("order_id");
                        int userId = rs.getInt("user_id");
                        System.out.println("orderId:" + orderId + ",userId：" + userId);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
