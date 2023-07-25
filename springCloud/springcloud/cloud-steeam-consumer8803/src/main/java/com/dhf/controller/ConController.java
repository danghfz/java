package com.dhf.controller;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   11:03
 */
@RestController
@Slf4j
public class ConController {

    @Bean(name = "input") // input-in-0
    public Consumer<String> consumer() {
        return new Consumer<String>() {
            @Override
            public void accept(String message) {
                log.info("8803消息：{}",message);
            }
        };
    }
}
