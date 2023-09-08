package com.dhf.listeners;

import org.activiti.engine.delegate.DelegateTask;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/04 16:05
 * 监听器
 */
public class MyTaskListener implements org.activiti.engine.delegate.TaskListener{
    /**
     * 指定个人任务的办理人
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        // 创建申请 && 事件名称  == 创建申请
        if ("create".equals(delegateTask.getEventName())&&
        "创建申请".equals(delegateTask.getName())){
            // 指定个人任务的办理人
            delegateTask.setAssignee("张三");
        }
    }
}
