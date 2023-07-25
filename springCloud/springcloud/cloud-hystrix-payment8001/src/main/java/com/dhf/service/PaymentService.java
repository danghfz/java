package com.dhf.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   13:31
 */
@Service
public class PaymentService {

    public String paymentInfo(Integer id) {
        return Thread.currentThread().getName() + " " + id;
    }

    @HystrixCommand(fallbackMethod = "errorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    }) // 该方法执行时间在 3s 以内，正常访问，否则走 errorHandler
    public String error() {
        double v = Math.random() * 6; // [0,6)
        int time = (int) v;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " " + time;
    }

    public String errorHandler() {
        return this.getClass() + " error出错了";
    }

    //======================================服务熔断=========================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            //如果滚动时间窗(默以10秒)内仅收到了10个请求，即使这9个请求都失败了， 断路器也不会打开。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 最少请求次数
            //该属性用来没置当断路器打开之后的休眠时间窗。休眠时间窗结束之后,会将断路器置为"半开”状态，尝试熔断的请求命令
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
           // 如果错误请求数的百分比超过 60,就把断路器没置为”打开”状态，否则就设置为"关闭”状态。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率达到多少跳闸
    })
    @GetMapping("/paymentCircuitBreaker")
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        UUID uuid = UUID.randomUUID();
        return Thread.currentThread().getName() + " " + uuid;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数，请重试";
    }
}
