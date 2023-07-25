package com.dhf.controller;

import com.dhf.client.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   17:19
 */
@RestController
@DefaultProperties(defaultFallback = "Global_Fallback") // 全局服务降级
public class OrderController {
    public String Global_Fallback(){
        return "对方系统繁忙，请稍后再试GLOBAL";
    }
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/paymentInfo")
    @HystrixCommand // 不用指明 使用哪个方法 自动使用全局异常
    public String paymentInfo( Integer id){
        return paymentService.paymentInfo(id);
    }
    @HystrixCommand(fallbackMethod = "errorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    @GetMapping("/err")
    public String err(){
        return paymentService.error();
    }
    public String errorHandler(){
        return "超时，请稍后再试";
    }
}
