package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   18:58
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Pro9002 {
    public static void main(String[] args) {
        SpringApplication.run(Pro9002.class,args);
    }
}
