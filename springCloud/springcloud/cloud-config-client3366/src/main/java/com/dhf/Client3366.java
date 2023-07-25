package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   14:47
 */
@SpringBootApplication
@EnableEurekaClient
public class Client3366 {
    public static void main(String[] args) {
        SpringApplication.run(Client3366.class,args);
    }
}
