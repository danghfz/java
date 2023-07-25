package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/26   13:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class Config3344 {
    public static void main(String[] args) {
        SpringApplication.run(Config3344.class,args);
    }
}
