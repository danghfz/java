package com.example.plug.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   10:38
 */
@Slf4j
@Controller
public class Customer {
    //延时队列
    private static final String DELAYED_QUEUE = "delayed_queue";
    @RabbitListener(queues = DELAYED_QUEUE)
    public void listen(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("当前时间{},收到消息{}",new Date(),msg);
    }
}
