package com.dhf.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   14:56
 */
@Data
@TableName("users")
public class User {
    Integer id;
    String username;
    String password;
}
