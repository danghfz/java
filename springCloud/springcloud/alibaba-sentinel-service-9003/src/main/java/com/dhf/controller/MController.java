package com.dhf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   17:02
 */
@RestController
public class MController {
    @Value("${server.port}")
    private String port;
    public static String[] arr = new String[3];
    static {
        arr[0] = "str : 0";
        arr[1] = "str : 1";
        arr[2] = "str : 2";
    }
    @GetMapping("/get/{id}")
    public String get(@PathVariable int id){
        return arr[id] + " forward " + port;
    }
}
