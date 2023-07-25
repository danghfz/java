package com.example.backup_exchanges.controller;

import com.example.backup_exchanges.config.RabbitConfig;
import com.sun.xml.internal.ws.api.model.MEP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import sun.misc.Contended;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   13:44
 */
@Controller
@Slf4j
public class WarningCustomer {

    @RabbitListener(queues = RabbitConfig.WARRING_QUEUE)
    public void warring(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("报警发现不可路由消息:{}",msg);
    }
}
