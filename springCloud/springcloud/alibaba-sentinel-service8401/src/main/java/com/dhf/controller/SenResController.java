package com.dhf.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dhf.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   9:54
 */
@RestController
public class SenResController {
    @GetMapping("/byResource")
    @SentinelResource(value = "get",blockHandler = "get_handler")
    public String get(){
        return "按照资源名限流";
    }
    public String get_handler(BlockException e){
        return "被限流了" + e.toString();
    }
    @GetMapping("/test")
    // 使用  CustomerBlockHandler  的   handlerException2这个方法处理限流
    @SentinelResource(value = "test",blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public String test(){
        return "test";
    }
}
