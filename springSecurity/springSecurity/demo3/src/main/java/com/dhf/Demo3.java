package com.dhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, //@Secured
prePostEnabled = true) //@PreAuthorize PostAuthorize
public class Demo3 {
    public static void main(String[] args) {
        SpringApplication.run(Demo3.class, args);
    }
}
