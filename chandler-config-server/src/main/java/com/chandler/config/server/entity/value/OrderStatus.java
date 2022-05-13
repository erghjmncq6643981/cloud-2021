package com.chandler.config.server.entity.value;

import lombok.Getter;

@Getter
public enum OrderStatus {
    STATUS_CREATED("STATUS_CREATED", "已创建"),
    STATUS_APPROVING("STATUS_APPROVING", "审批中"),
    STATUS_REJECTED("STATUS_REJECTED", "被踢退"),
    STATUS_APPROVED("STATUS_APPROVED", "已执行"),
    STATUS_REVERT("STATUS_REVERT", "已回退"),
    STATUS_WITHDRAW("STATUS_WITHDRAW", "已撤回");

    private String name;
    private String label;

    OrderStatus(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public static  OrderStatus valueOfString(String name){
        for (OrderStatus t : OrderStatus.values()) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    public static  OrderStatus labelOfString(String name){
        for (OrderStatus t : OrderStatus.values()) {
            if (t.getLabel().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}