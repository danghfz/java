package com.dhf.service.impl;

import com.dhf.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   17:16
 */
@EnableBinding(Source.class) // 消息推送的管道，消息发送者 Source
@Service
public class IMessageServiceImpl implements IMessageService {
//    @Resource
//    private MessageChannel output; // 消息发生管道
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public String send(String message) {
        message = UUID.randomUUID().toString();
//        output.send(MessageBuilder.withPayload(message).build());
        streamBridge.send("producer-out-0", MessageBuilder.withPayload(message).build());
        System.out.println("producer-out-0 发送: " + message);
        return null;
    }
}
