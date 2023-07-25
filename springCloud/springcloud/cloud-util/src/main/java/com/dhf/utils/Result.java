package com.dhf.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/8/22   22:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Result {
    private Integer code;
    private String message;
    private Map<String, Object> data;
    public Result setDate(String key,Object value){
        if (this.data == null){
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
        return this;
    }

    public static Result ok() {
        Result result = new Result();
        return result.setCode(200);
    }

    public static Result error() {
        Result result = new Result();
        return result.setCode(201);
    }
}
