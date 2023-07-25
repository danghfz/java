package com.example.bootmq.controller;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   9:52
 * 消费者，消费者是通过监听获取
 */
@Slf4j
@RestController
public class Customer {

    //接收消息，监听QD队列
    @RabbitListener(queues = "QD")
    public void receive(Message message, Channel channel){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("当前时间：{}，收到死信队列消息：{}", new Date(),msg);
    }

}
