package com.dhf.msgType.sequenceMsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 12:29
 * 顺序消息生产者
 */
public class OrderProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("order_producer_group");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                Integer orderId = i;
                byte[] body = ("order " + i).getBytes();
                Message msg = new Message("order_topic", "order_tag", body);
                // send(Message msg, MessageQueueSelector selector, Object arg)
                // @param arg Argument to work aLong with message queue selector 、
                producer.send(msg, new MessageQueueSelector() {
                    // 消息选择器中的 arg 参数就是 send 方法中的 arg 参数
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                        int index = (Integer)arg % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
