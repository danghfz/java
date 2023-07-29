package com.dhf.msgType.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.text.SimpleDateFormat;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 12:58
 * 延时消息
 */
public class DelayMsg {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("delay_producer_group");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                byte[] body = ("delay " + i).getBytes();
                Message msg = new Message("delay_topic", "delay_tag", body);
                msg.setKeys(i + "");
                // 设置延时等级 3 表示 10s 后发送
                msg.setDelayTimeLevel(3);
                producer.send(msg);
                System.out.println(new SimpleDateFormat("HH:mm:ss")
                        .format(System.currentTimeMillis())
                        + " send delay msg ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}