package com.example.plug.controller;

import com.example.plug.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   10:38
 * 生产者
 */
@Slf4j
@Controller
public class Procter {
    private static final String DELAYED_EXCHANGE = "delayed_exchange";
    @Autowired
    RabbitTemplate rabbitTemplate;
    //基于插件的延时队列
    @RequestMapping("/test/send")
    public void sendMessage(String message,Integer time){
        log.info("当前时间{}，发送一条时长{}毫秒信息到延迟交换机:{}",
                new Date(),time,message);
        //发送
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE,"delayed_routing_key",message,msg->{
          msg.getMessageProperties().setDelay(time);
          return msg;
        });
    }
}
