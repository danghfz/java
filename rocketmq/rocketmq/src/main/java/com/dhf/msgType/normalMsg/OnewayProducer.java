package com.dhf.msgType.normalMsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.logging.InnerLoggerFactory;

import java.util.logging.Logger;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 11:02
 * 单向消息生产者
 */
public class OnewayProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                byte[] bytes = ("single_topic" + i).getBytes();
                Message msg = new Message("single_topic", "single_tag", bytes);
                producer.sendOneway(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
