package com.local.boot.pojo;

import lombok.*;

/**
 * @author 党
 * @version 1.0
 * 2022/5/30   11:53
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Pet {
    private String name;
    private Double weight;
}
