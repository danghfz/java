package com.dhf.msgType.sequenceMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 14:36
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");
        consumer.setNamesrvAddr("www.danghf.rocketmq:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("delay_topic","*");
        consumer.registerMessageListener(new MessageListenerConcurrently(){
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt msg : list){
                    String keys = msg.getKeys();
                    System.out.println(new SimpleDateFormat("HH:mm:ss")
                            .format(System.currentTimeMillis())
                            + " receive msg keys: " + keys);
                }
                return null;
            }
        });
        consumer.start();

    }
}
