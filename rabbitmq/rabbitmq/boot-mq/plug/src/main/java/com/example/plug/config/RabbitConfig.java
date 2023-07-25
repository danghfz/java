package com.example.plug.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/7/13   10:22
 */
@Configuration
public class RabbitConfig {
    //定义延时交换机
    private static final String DELAYED_EXCHANGE = "delayed_exchange";
    //延时队列
    private static final String DELAYED_QUEUE = "delayed_queue";
    private static final String DELAYED_ROUTING_KEY = "delayed_routing_key";

    @Bean //自定义类型交换机
    public CustomExchange delayedExchange(){
        Map<String, Object> args = new HashMap<>();
        //自定义交换机的类型
        args.put("x-delayed-type", "direct");
        /**
         * @Param String name
         * @Param String type 交换机类型
         * @param boolean durable 持久化
         * @Param boolean autoDelete 自动删除
         */
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, args);
    }


    @Bean(name = DELAYED_QUEUE)
    public Queue queue(){
        return QueueBuilder.durable(DELAYED_QUEUE).build();
    }

    @Bean
    public Binding binding(@Autowired Queue queue,
                           @Qualifier("delayedExchange") CustomExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
