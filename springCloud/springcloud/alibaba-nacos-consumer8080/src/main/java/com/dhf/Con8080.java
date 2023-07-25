package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   19:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Con8080 {
    public static void main(String[] args) {
        SpringApplication.run(Con8080.class,args);
    }
}
