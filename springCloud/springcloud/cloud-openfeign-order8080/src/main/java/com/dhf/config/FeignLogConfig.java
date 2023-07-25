package com.dhf.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   12:48
 */
@Configuration
public class FeignLogConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL; // 日志打印级别
    }
}
