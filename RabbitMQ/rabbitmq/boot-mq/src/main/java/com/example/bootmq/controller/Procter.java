package com.example.bootmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   9:45
 * 生产者
 */
@Slf4j
@RequestMapping("ttl")
@RestController
public class Procter {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendMessage",
            params = {"message"})
    //发消息
    public void sendMessage(String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        /**
         * @Param exchange 交换机
         * @Param routingKey
         * @Param object 消息内容
         */
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列:  " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 20S 的队列:  " + message);
    }

    @RequestMapping(value = "/sendMessageTime",
            params = {"message"})
    //发消息
    /**
     * @Param message 消息
     * @Param time 时间毫秒 ms
     */
    public void sendMessageTime(String message, String time) {

        log.info("当前时间：{},发送一条信息给三个 TTL 队列:{}", new Date(), message);
        /**
         * @Param exchange 交换机
         * @Param routingKey
         * @Param object 消息内容
         */
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列:  " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 20S 的队列:  " + message);
        rabbitTemplate.convertAndSend("X",
                "XC",
                message,
                (correlationData) -> {
                    correlationData.getMessageProperties().setExpiration(time);
                    return correlationData;
                });
    }
}
