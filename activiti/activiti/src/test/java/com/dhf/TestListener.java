package com.dhf;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/04 16:15
 */
public class TestListener {
    /**
     * 部署流程
     */
    @Test
    public void deployProcess() {
        // 创建ProcessEngineConfiguration对象
        // 创建ProcessEngine对象
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/demo_listen.bpmn20.xml")
                .addClasspathResource("png/demo_listen.png")
                .disableSchemaValidation()
                .deploy();
        // 部署流程定义
        System.out.println("部署流程ID：" + deploy.getId());
        System.out.println("部署流程名称：" + deploy.getName());

    }

    /**
     * 启动流程
     */
    @Test
    public void startProcess() {
        ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .startProcessInstanceByKey("demo_listen");
    }
    @Test
    public void completeTask() {
        String taskId = "22502";
        ProcessEngines.getDefaultProcessEngine()
                .getTaskService()
                .complete(taskId);
    }
}
