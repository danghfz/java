package com.example.confirm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   16:47
 * 回调接口
 */
@Slf4j
@Component
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {
    //注入rabbitTemplate
    @Resource
    RabbitTemplate rabbitTemplate;
    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行
    //Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void init(){
        //确认发布
        rabbitTemplate.setConfirmCallback(this);
        //注入消息回退
        rabbitTemplate.setReturnsCallback(this);
    }
    /***
     * 交换机确认回调的方法
     * @param correlationData 回调消息id及消息
     * @param ack 收到消息true，没有收到false
     * @param cause 成功null，失败->失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData!=null?correlationData.getId():"";
        if (ack){//成功

            log.info("交换机收到消息:id={}",id);
        }else {
            log.info("交换机未收到消息:id={},原因为{}",id,cause);
        }
    }

    //消息不可达目的地时，进行回退

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String exchange = returnedMessage.getExchange();
        byte[] body = returnedMessage.getMessage().getBody();
        String msg = new String(body);
        String replyText = returnedMessage.getReplyText();
        log.info("路由{},消息{},原因{}",exchange,msg,replyText);
    }
}
