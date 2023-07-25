package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/26   18:02
 */
@SpringBootApplication
@EnableEurekaClient
public class Client3355 {
    public static void main(String[] args) {
        SpringApplication.run(Client3355.class, args);
    }
}
