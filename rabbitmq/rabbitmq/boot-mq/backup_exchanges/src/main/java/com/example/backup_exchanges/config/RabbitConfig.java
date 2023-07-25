package com.example.backup_exchanges.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   11:20
 */
@Configuration
public class RabbitConfig {
    //主交换机
    public static final String EXCHANGE = "MASTER_EXCHANGE";
    //备份交换机
    public static final String BACKUP_EXCHANGE = "BACKUP_EXCHANGE";
    //普通队列
    public static final String QUEUE = "MASTER_QUEUE";
    //备份队列
    public static final String BACKUP_QUEUE = "BACKUP_QUEUE";
    //警告队列
    public static final String WARRING_QUEUE = "WARRING_QUEUE";
    //routingKey
    public static final String EX_QU_Key = "key";

    @Bean(name = EXCHANGE)
    public DirectExchange directExchange(){
        HashMap<String, Object> arguments = new HashMap<>();
        /**
         * 将直接交换机和扇出交换机（备份交换机）进行联系
         */
        arguments.put("alternate-exchange",BACKUP_EXCHANGE);
        return  ExchangeBuilder.directExchange(EXCHANGE).durable(true).withArguments(arguments).build();
    }
    //备份交换机
    @Bean(name = BACKUP_EXCHANGE)
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE);
    }
    @Bean(name = QUEUE)
    public Queue queue(){
//        return QueueBuilder.durable(QUEUE).build();
        HashMap<String, Object> arguments = new HashMap<>();
//        arguments.put("x-max-priority",10);
        return QueueBuilder.durable().withArguments(arguments).build();
    }
    @Bean(name = BACKUP_QUEUE)
    public Queue backUpQueue(){
        return QueueBuilder.durable(BACKUP_QUEUE).build();
    }
    @Bean(name = WARRING_QUEUE)
    public Queue warringQueue(){
        return QueueBuilder.durable(WARRING_QUEUE).build();
    }

    @Bean
    public Binding binding(@Qualifier(EXCHANGE) DirectExchange directExchange,
                           @Qualifier(QUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(EX_QU_Key);

    }
    //备份交换机个备份队列
    @Bean
    public Binding binding2(@Qualifier(BACKUP_EXCHANGE) FanoutExchange fanoutExchange,
                            @Qualifier(BACKUP_QUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);

    }
    //备份交换机和报警队列
    @Bean
    public Binding binding3(@Qualifier(BACKUP_EXCHANGE) FanoutExchange fanoutExchange,
                            @Qualifier(WARRING_QUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
