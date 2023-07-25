package com.dhf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 党
 * @version 1.0
 * 2022/9/3   13:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Order2001 {
    public static void main(String[] args) {
        SpringApplication.run(Order2001.class,args);
    }
}
