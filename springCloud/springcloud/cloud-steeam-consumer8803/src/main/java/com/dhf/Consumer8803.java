package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   11:02
 */
@SpringBootApplication
@EnableEurekaClient
public class Consumer8803 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer8803.class,args);
    }
}
