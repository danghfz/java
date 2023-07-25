package com.local.dhf.bootmybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   14:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Emp")
public class Emp {
    /**
     * id BIGINT(20) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
     * name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
     * age INT(11) NULL DEFAULT NULL COMMENT '年龄',
     * email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
     * PRIMARY KEY (id)
     */
    private BigInteger id;
    private String name;
    private Integer age;
    private String email;
}
