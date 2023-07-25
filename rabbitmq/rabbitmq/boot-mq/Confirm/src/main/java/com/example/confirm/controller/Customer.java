package com.example.confirm.controller;

import com.example.confirm.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   14:59
 * 消费者
 */
@Slf4j
@Controller
public class Customer {
    @RabbitListener(queues = RabbitConfig.CONFIRM_QUEUE)
    public void listen(Message message){
        byte[] body = message.getBody();
        log.info("接收时间{},消息内容{}",new Date(),new String(body));
    }
}
