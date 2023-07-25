package com.dhf.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   19:27
 */
@Component
@FeignClient(name = "nacos-producer")
public interface ProClient {
    @GetMapping("/hello")
    String hello();
}
