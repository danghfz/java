package com.dhf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/01 10:48
 */
public class TestAssigneeUel {
    /**
     * 测试流程部署
     */
    @Test
    public void deployment(){
        RepositoryService repositoryService = ProcessEngines
                .getDefaultProcessEngine()
                .getRepositoryService();
        Deployment deployment = repositoryService
                .createDeployment()
                .name("出差申请流程uel")
                .addClasspathResource("bpmn/evection_uel.bpmn20.xml")
                .addClasspathResource("png/evection_uel.png")
                // 禁止文件校验
                // org.activiti.bpmn.exceptions.XMLException: cvc-complex-type.2.4.a: 发现了以元素 'incoming' 开头的无效内容
                .disableSchemaValidation()
                .deploy();
        System.out.println("流程部署id"+deployment.getId());
    }
    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance(){
        // 流程定义key
        String processDefinitionKey = "myEvection_uel";
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 设置assignee的取值，用户可以设置流程的执行人
        HashMap<String, Object> args = new HashMap<>();
        args.put("assignee0", "张三");
        args.put("assignee1", "李四(经理)");
        args.put("assignee2", "王五(总经理)");
        args.put("assignee3", "赵六(财务)");
        // 启动流程实例
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey,args);

        // 输出内容
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("当前活动ID：" + processInstance.getActivityId());
    }
}
