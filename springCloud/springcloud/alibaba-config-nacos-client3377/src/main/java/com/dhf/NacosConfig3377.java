package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/31   9:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfig3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfig3377.class,args);
    }
}
