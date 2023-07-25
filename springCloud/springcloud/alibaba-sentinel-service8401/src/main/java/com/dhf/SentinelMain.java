package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/31   18:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain.class,args);
    }
}
