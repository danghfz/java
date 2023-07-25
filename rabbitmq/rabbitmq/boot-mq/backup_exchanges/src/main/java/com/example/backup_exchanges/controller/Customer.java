package com.example.backup_exchanges.controller;

import com.example.backup_exchanges.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   13:51
 */
@Slf4j
@Controller
public class Customer{

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void con(Message message){
        log.info("接收到消息{}",message);
    }
}
