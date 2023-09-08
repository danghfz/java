package com.dhf;

import com.dhf.pojo.Evection;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/04 17:22
 */
public class TestVariable {
    @Test
    public void deployment() {
        Deployment deployment = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("测试变量")
                .addClasspathResource("bpmn/evection_global.bpmn20.xml")
                .addClasspathResource("png/evection_global.png")
                .disableSchemaValidation()
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    @Test
    public void deleteDeployment() {
        String deploymentId = "27501";
        ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .deleteDeployment(deploymentId);
    }

    @Test
    public void startProcessInstance() {
        String processDefinitionKey = "evection_global";
        HashMap<String, Object> args = new HashMap<>();
        Evection evection = new Evection();
        // days >=3 总经理审批，days < 3 人事审批
        evection.setDays(2);
        args.put("evection", evection);
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey, args);
        System.out.println(processInstance.getProcessDefinitionId());
        System.out.println(processInstance.getName());
    }

    @Test
    public void completeTask() {
        TaskService taskService = ProcessEngines.getDefaultProcessEngine()
                .getTaskService();
        Task task;
        while ((task = taskService.createTaskQuery()
                .processDefinitionKey("evection_global")
                .singleResult()) != null) {
            //System.out.println(task.getId());
            System.out.println("taskName: "+task.getName());
            taskService.complete(task.getId());
        }
    }
}
