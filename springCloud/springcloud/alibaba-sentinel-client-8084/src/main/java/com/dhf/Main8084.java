package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   17:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Main8084 {
    public static void main(String[] args) {
        SpringApplication.run(Main8084.class,args);
    }
}
