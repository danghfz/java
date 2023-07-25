package com.example.backup_exchanges.controller;

import com.example.backup_exchanges.config.RabbitConfig;
import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   13:46
 */
@Slf4j
@Controller
public class Pro {
    @Resource
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/backExchange")
    public void backExchange(@RequestParam("message")String message,
                             @RequestParam(value="key",required = false,defaultValue = "") String key) {
        log.info("发送消息{}", message);
        if (key.equals("")){
            key = RabbitConfig.EX_QU_Key;
        }
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                key,
                message,
                msg->{//设置优先级
                    MessageProperties messageProperties = msg.getMessageProperties();
                    messageProperties.setPriority(5);
                    return msg;
                }
        );
    }
}
