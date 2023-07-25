package com.dhf.controller;

import com.dhf.pojo.Payment;
import com.dhf.service.PaymentService;
import com.dhf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 党
 * @version 1.0
 * 2022/8/22   22:14
 */
@RestController
@RequestMapping("/payment/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/create")
    public Result create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        if (save) {
            return Result.ok().setMessage("插入成功").setDate("payment", payment);
        } else {
            return Result.error().setMessage("插入失败");
        }
    }
    @GetMapping("/getPaymentById/{id}")
    public Result getPaymentById(@PathVariable String id){
        Payment payment = paymentService.getById(id);
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (payment == null)
            return Result.error().setMessage("空");
        return Result.ok().setMessage("查询成功").setDate("payment", payment).setDate("port",serverPort);
    }
    @GetMapping("/payment/discovery")
    public Result discovery(){
        List<String> services = discoveryClient.getServices(); // 获取所以服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            instance.getHost();
            instance.getPort();
            instance.getServiceId();
            instance.getUri();
        }
        return Result.ok().setDate("service",services).setDate("instances",instances);
    }
}
