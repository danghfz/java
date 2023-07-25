package com.local.boot.pojo;

import lombok.*;

/**
 * @author 党
 * @version 1.0
 * 2022/5/26   17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String name;
    private Integer age;
}
