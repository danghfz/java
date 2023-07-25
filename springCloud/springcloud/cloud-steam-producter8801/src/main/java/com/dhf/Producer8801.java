package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   17:12
 */
@SpringBootApplication
@EnableEurekaClient
public class Producer8801 {
    public static void main(String[] args) {
        SpringApplication.run(Producer8801.class,args);
    }
}
