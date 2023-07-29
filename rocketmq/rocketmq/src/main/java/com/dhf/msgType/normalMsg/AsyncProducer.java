package com.dhf.msgType.normalMsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 10:40
 * 异步消息生产者
 */
public class AsyncProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("async_producer");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        // 指定异步发送失败后不进行重试发送 default:2
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 指定新创建的Topic的Queue数量为 2 ，默认为 4
        producer.setDefaultTopicQueueNums(2);

        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                byte[] body = ("async_topic," + i).getBytes();
                try {
                    Message msg = new Message("async_topic", "async_tag", body);
                    // 异步发送。指定回调
                    producer.send(msg, new SendCallback() {
                        // 当producer接收到MQ发送来的ACK后就会触发该回调方法的执行
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            System.out.println(sendResult);
                        }

                        @Override
                        public void onException(Throwable e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // end-for
            // sleep一会儿
            // 由于采用的是异步发送，所以若这里不sleep，
            // 则消息还未发送就会将producer给关闭，报错
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
