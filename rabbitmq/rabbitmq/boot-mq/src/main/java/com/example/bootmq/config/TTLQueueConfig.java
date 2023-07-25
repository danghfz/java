package com.example.bootmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   9:13
 *
 */
@Configuration
public class TTLQueueConfig {
    //普通交换机1
    private static final String X_EXCHANGE = "X";
    //死信交换机
    private static final String Y_DEAD_EXCHANGE = "Y";
    //普通队列1
    private static final String QUEUE_A = "QA";
    //普通队列2
    private static final String QUEUE_B = "QB";
    //死信队列
    private static final String DEAD_QUEUE = "QD";

    //声明普通交换机x
    @Bean(name = X_EXCHANGE)
    public DirectExchange exchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    @Bean(name = Y_DEAD_EXCHANGE)
    public DirectExchange dead_exchange(){
        return new DirectExchange(Y_DEAD_EXCHANGE);
    }
    //队列
    @Bean(name = QUEUE_A)
    public Queue queue_a(){
        HashMap<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_EXCHANGE);
        //设置RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置ttl,10s
        arguments.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_A)
                .withArguments(arguments)
                .build();
    }
    @Bean(name = QUEUE_B)
    public Queue queue_b(){
        HashMap<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_EXCHANGE);
        //设置RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置ttl,20s
        arguments.put("x-message-ttl",20000);
        return QueueBuilder.durable(QUEUE_B)
                .withArguments(arguments)
                .build();
    }
    //死信队列
    @Bean(name = DEAD_QUEUE)
    public Queue dead_queue(){
        return QueueBuilder.durable(DEAD_QUEUE)
                .build();
    }
    //绑定
    @Bean
    public Binding queueA_X(@Qualifier(X_EXCHANGE) DirectExchange directExchange,
                            @Qualifier(QUEUE_A) Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("XA");
    }
    //绑定
    @Bean
    public Binding queueB_X(@Qualifier(X_EXCHANGE) DirectExchange directExchange,
                            @Qualifier(QUEUE_B) Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("XB");
    }
    @Bean
    public Binding binding(@Qualifier(DEAD_QUEUE) Queue queue,
                           @Qualifier(Y_DEAD_EXCHANGE) DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("YD");

    }

    //优化，增加一个单独的队列，不设置ttl，在发送消息的时候决定ttl
    private static final String QUEUE_C = "AC";
    @Bean(name = QUEUE_C)
    public Queue queue(){
        HashMap<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_EXCHANGE);
        //设置RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_C)
                .withArguments(arguments)
                .build();
    }
    //绑定
    @Bean
    public Binding bindingC(@Qualifier(QUEUE_C) Queue queue,
                           @Qualifier(X_EXCHANGE) DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("XC");
    }

}
