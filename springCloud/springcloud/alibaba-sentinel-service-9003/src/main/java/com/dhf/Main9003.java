package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sun.applet.Main;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   16:59
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9003 {
    public static void main(String[] args) {
        SpringApplication.run(Main9003.class,args);
    }
}
