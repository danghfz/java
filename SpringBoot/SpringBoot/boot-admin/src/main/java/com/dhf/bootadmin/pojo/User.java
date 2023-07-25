package com.dhf.bootadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 党
 * @version 1.0
 * 2022/6/18   23:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;

}
