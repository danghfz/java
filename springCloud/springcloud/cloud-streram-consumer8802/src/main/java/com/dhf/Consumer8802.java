package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   18:17
 */
@SpringBootApplication
@EnableEurekaClient
public class Consumer8802 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer8802.class,args);
    }
}
