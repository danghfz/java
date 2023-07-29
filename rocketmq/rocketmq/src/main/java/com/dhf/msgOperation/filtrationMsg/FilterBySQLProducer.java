package com.dhf.msgOperation.filtrationMsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/29 14:27
 */
public class FilterBySQLProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        producer.start();
        for (int i = 0 ; i < 10 ; i++) {
            try {
                byte[] body = ("Hi," + i).getBytes();
                Message msg = new Message("myTopic", "myTag", body);
                // 设置属性 Property ，后面根据属性进行sql过滤
                msg.putUserProperty("age", i + "");
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
