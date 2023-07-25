package com.local.boot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 党
 * @version 1.0
 * 2022/6/12   11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {
    private String name;
    private Integer age;
}
