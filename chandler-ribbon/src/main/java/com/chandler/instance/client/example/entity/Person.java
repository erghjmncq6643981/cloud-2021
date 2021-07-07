package com.chandler.instance.client.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 类功能描述
 *
 * @author 钱丁君-chandler 2019/5/17下午2:02
 * @since 1.8
 */
@Data
@Schema(description = "测试对象")
public class Person {
    @Schema(description = "姓名", example = "chandler")
    private String name;
    @Schema(description = "年龄", example = "18")
    private Integer age;
    @Schema(description = "性别", example = "man")
    private String sex;
}
