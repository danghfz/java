package com.dhf.msgType.transactionMsg;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/29 11:13
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer_group");
        producer.setNamesrvAddr("www.danghf.rocketmq:9876");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());
        // 为生产者指定线程池
        producer.setExecutorService(poolExecutor);
        // 为生产者指定监听器
        producer.setTransactionListener(new ICBCTransactionListener());
        producer.start();
        // 发送消息
        String[] tags = {"TAGA","TAGB","TAGC"};
        for (int i = 0 ; i < 3 ; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TTopic", tags[i], body);
            // 发送事务消息
            // 第二个参数用于指定在执行本地事务时要使用的业务参数
            SendResult sendResult =producer.sendMessageInTransaction(msg,null);
            System.out.println("发送结果为：" +sendResult.getSendStatus());
        }
        producer.shutdown();
    }
}
