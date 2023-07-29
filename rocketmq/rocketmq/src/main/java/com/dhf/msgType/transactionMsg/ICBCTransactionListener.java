package com.dhf.msgType.transactionMsg;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/29 11:07
 * 监听器
 */
public class ICBCTransactionListener implements TransactionListener {
    //
    //

    /**
     * 回调操作方法 消息预提交成功就会触发该方法的执行，用于完成本地事务
     *
     * @param msg
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object o) {
        System.out.println("预提交消息成功：" + msg);
        // 假设接收到TAGA的消息就表示扣款操作成功，TAGB的消息表示扣款失败，
        // TAGC表示扣款结果不清楚，需要执行消息回查
        if (StringUtils.equals("TAGA", msg.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.equals("TAGB", msg.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }


    /**
     * 消息回查方法
     * 引发消息回查的原因最常见的有两个：
     * 1)回调操作返回UNKNWON
     * 2)TC没有接收到TM的最终全局事务确认指令
     *
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println("执行消息回查" + messageExt.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
