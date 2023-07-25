package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 党
 * @version 1.0
 * 2022/8/23   19:19
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7002Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7002Application.class, args);
    }
}
