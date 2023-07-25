package com.example.confirm.controller;

import com.example.confirm.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   14:59
 * 生产者
 */
@Slf4j
@RequestMapping("/test")
@Controller
public class Procter {
    @Resource
    RabbitTemplate rabbitTemplate;


    @RequestMapping("/sendMessage")
    public void sendMessage(String msg) {
        int id = new Date().getMinutes();
        CorrelationData correlationData = new CorrelationData(id+"");

        log.info("当前时间{},消息内容:{}", new Date(), msg);
        rabbitTemplate.convertAndSend(
                RabbitConfig.CONFIRM_EXCHANGE,
                RabbitConfig.CONFIRM_ROUTING_KEY+"1",
                msg,
                correlationData
        );
    }
}
