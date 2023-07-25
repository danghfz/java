package com.example.confirm.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   14:59
 */
@Configuration
public class RabbitConfig {
    public static final String CONFIRM_EXCHANGE = "CONFIRM_EXCHANGE";
    public static final String CONFIRM_QUEUE = "CONFIRM_QUEUE";
    public static final String CONFIRM_ROUTING_KEY = "CONFIRM_ROUTING_KEY";
    @Bean //交换机
    public DirectExchange directExchange(){
        return new DirectExchange(CONFIRM_EXCHANGE);
    }
    @Bean
    public Queue queue(){
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }
    @Bean
    public Binding binding(@Autowired Queue queue,
                           @Qualifier("directExchange") DirectExchange exception){
        return BindingBuilder.bind(queue).to(exception).with(CONFIRM_ROUTING_KEY);
    }
}
