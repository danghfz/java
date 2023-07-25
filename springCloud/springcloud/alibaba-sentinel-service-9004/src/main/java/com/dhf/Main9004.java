package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   17:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9004 {
    public static void main(String[] args) {
        SpringApplication.run(Main9004.class,args);
    }
}
