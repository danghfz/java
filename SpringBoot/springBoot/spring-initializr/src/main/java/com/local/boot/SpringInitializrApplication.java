package com.local.boot;

import com.local.boot.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <img src="https://tse3-mm.cn.bing.net/th/id/OIP-C.g8pSBnK5l1JsSavB0rzbhQHaEA?w=305&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7"/>
 * <img src="https://tse3-mm.cn.bing.net/th/id/OIP-C.tLSvmo1stI-LIoC8qZJOVAHaGe?w=207&h=181&c=7&r=0&o=5&dpr=1.25&pid=1.7"/>
 */

@SpringBootApplication
@EnableConfigurationProperties(Person.class)
public class SpringInitializrApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringInitializrApplication.class, args);
    }
}
