package com.dhf.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   17:11
 */
@Component
@FeignClient(value = "alibaba-server")
public interface ServerClient {
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id);
}
