package com.dhf.gateway;

import com.dhf.pojo.Evection;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/07 16:20
 */
public class TestExclusiveGateway {
    String key = "exclusiveGateway";
    /**
     * 部署流程
     */
    @Test
    public void deployment(){
        Deployment deploy = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("排他网关测试")
                .addClasspathResource("bpmn/exclusiveGateway.bpmn20.xml")
                .addClasspathResource("png/exclusiveGateway.png")
                .disableSchemaValidation()
                .deploy();
        System.out.println("部署ID：" + deploy.getId());
        System.out.println("部署名称：" + deploy.getName());
    }
    /**
     * 启动流程
     */
    @Test
    public void startProcess(){
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .startProcessInstanceByKey(key);
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
        System.out.println("流程名称: "+processInstance.getName());
    }

    /**
     * 完成任务
     */
    @Test
    public void complete(){
        Task task = findTask(key);
        String taskId = task.getId();
        HashMap<String, Object> map = new HashMap<>();
        Evection evection = new Evection();
        evection.setDays(3);
        map.put("evection",evection);
        ProcessEngines.getDefaultProcessEngine()
                .getTaskService()
                .complete(taskId,map);
    }
    @Test
    public void deleteDeployment(){
        // 根据key 得到部署id
        Deployment deployment = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeploymentQuery()
                .processDefinitionKey(key)
                .singleResult();
        if (deployment!=null){
            ProcessEngines.getDefaultProcessEngine()
                    .getRepositoryService()
                    .deleteDeployment(deployment.getId(),true);
        }

    }
    public Task findTask(String key){
        return ProcessEngines.getDefaultProcessEngine()
                .getTaskService()
                .createTaskQuery()
                .processDefinitionKey(key)
                .singleResult();
    }

}
