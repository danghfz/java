package com.dhf.msgType.normalMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.logging.Logger;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/27 19:51
 * 同步消息生产者
 */
public class SyncProducer {
    public static void main(String[] args) {
        // 创建生产者
        // new DefaultMQProducer(producerGroup) producerGroup:生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("sync_producer");
        // 设置NameServer地址
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        // 设置当发送失败时重试发送的次数，默认为 2 次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);
        try {
            // 启动生产者
            producer.start();
            // 发送消息
            for (int i = 0; i < 10; i++) {
                // public Message(String topic, byte[] body) {
                byte[] body = ("hello" + i).getBytes();
                // public Message(String topic, String tags, byte[] body)
                Message msg = new Message("sync_topic", "sync_tag", body);
                SendResult sendResult = producer.send(msg);
                System.out.println("消息发送结果：" + sendResult);
            }
        } catch (MQClientException | MQBrokerException | RemotingException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭生产者
            producer.shutdown();
        }
    }
}
