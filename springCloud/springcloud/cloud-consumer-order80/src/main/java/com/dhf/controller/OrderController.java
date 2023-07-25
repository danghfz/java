package com.dhf.controller;

import com.dhf.lb.impl.MyLb;
import com.dhf.pojo.Payment;
import com.dhf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/8/23   15:10
 */
@RestController
@RequestMapping("/order/order/consumer")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
//    private final String url = "http://localhost:8001/payment/"; // 单机根据 ip:port
    private final String url = "http://CLOUD-PAYMENT-SERVICE/payment/"; // 集群后要使用服务名

    @GetMapping("/create")
    public Result create(@RequestBody Payment payment) {
        return restTemplate.postForObject(url + "payment/create", payment, Result.class);
    }

    @GetMapping("/getPaymentById/{id}")
    public Result getPaymentById(@PathVariable String id) {
        return restTemplate.getForObject(url + "payment/getPaymentById/" + id, Result.class);
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private MyLb loadBalancer;
    public Object testLoadBalancer(){
        // 根据服务名获取服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        // 负载均衡得到服务
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        Object forObject = restTemplate.getForObject(uri, Object.class);
        return forObject;
    }
}
