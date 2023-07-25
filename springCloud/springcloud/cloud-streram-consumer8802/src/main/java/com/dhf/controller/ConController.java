package com.dhf.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.function.Consumer;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   18:18
 */
@Controller
//@EnableBinding(Sink.class)
public class ConController {
    //    @StreamListener(Sink.INPUT)
//    public void message(Message<String> message) {
//        System.out.println(message.getPayload());
//    }
    @Bean(name = "input") // input-in-0
    Consumer<String> consumer() {
        return str -> System.out.println("消息：" + str);
    }
}
