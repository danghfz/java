package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   11:50
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Order8080 {
    public static void main(String[] args) {
        SpringApplication.run(Order8080.class,args);
    }
}
