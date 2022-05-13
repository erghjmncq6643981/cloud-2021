/*
 * configservice-parent
 * 2021/3/22 4:29 PM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.entity.value;

/**
 * Description: <br>
 * Copyright: 数禾科技 Copyright(c)
 *
 * @author 钱丁君-chandler 2021/3/22 4:29 PM
 * @version 1.0.0
 * @since 1.8
 */
public enum ConnectorType {
    AND("&&"), OR("||"), NOT("!"), NULL("");

    ConnectorType(String connector) {
        this.connector = connector;
    }

    private String connector;

    public String getConnector() {
        return connector;
    }

    public static ConnectorType valueOfConnector(String connector){
        for (ConnectorType t : ConnectorType.values()) {
            if (t.getConnector().equalsIgnoreCase(connector)) {
                return t;
            }
            if (t.name().equalsIgnoreCase(connector)) {
                return t;
            }
        }
        return AND;
    }
}
