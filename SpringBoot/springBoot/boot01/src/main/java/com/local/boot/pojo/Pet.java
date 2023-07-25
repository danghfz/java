package com.local.boot.pojo;

import lombok.*;

/**
 * @author 党
 * @version 1.0
 * 2022/5/26   17:46
 */
@Data // get + set
@ToString
@AllArgsConstructor // 全参构造器
@NoArgsConstructor // 无参构造器
@EqualsAndHashCode
public class Pet {
    private String name;
}
