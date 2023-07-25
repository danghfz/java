package com.dhf.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName payment
 */
@Data
public class Payment implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     *
     */
    private String serial;

    private static final long serialVersionUID = 1L;
}