package com.dhf.gateway;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/07 16:50
 */
public class TestParallelGateway {
    String key = "parallelGateway";
    /**
     * 部署流程
     */
    @Test
    public void deployment(){
        Deployment deployment = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("并行网关测试")
                .addClasspathResource("bpmn/parallelGateway.bpmn20.xml")
                .addClasspathResource("png/parallelGateway.png")
                .disableSchemaValidation()
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }
    /**
     * 启动流程
     */
    @Test
    public void startProcess(){
        ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .startProcessInstanceByKey("parallelGateway");
    }
    @Test
    public void completeTask(){
        List<Task> tasks = getTask(key);
        TaskService taskService = ProcessEngines.getDefaultProcessEngine()
                .getTaskService();
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
    }
    public List<Task> getTask(String key){
        return ProcessEngines.getDefaultProcessEngine()
                .getTaskService()
                .createTaskQuery()
                .processDefinitionKey(key)
                .list();
    }
}
