package com.local.dhf.bootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   13:21
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class City {
    private Integer id;
    private String name;
    private String state;
    private String country;
}
