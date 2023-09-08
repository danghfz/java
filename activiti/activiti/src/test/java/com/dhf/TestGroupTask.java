package com.dhf;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/06 15:56
 * 测试组任务
 */
public class TestGroupTask {
    /**
     * 部署流程
     */
    @Test
    public void deployment() {
        Deployment deployment = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("测试组任务")
                .addClasspathResource("bpmn/groupTask.bpmn20.xml")
                .addClasspathResource("png/groupTask.png")
                .disableSchemaValidation()
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    /**
     * 创建流程实例
     */
    @Test
    public void startProcessInstance() {
        ProcessInstance groupTask = ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .startProcessInstanceByKey("groupTask");
        System.out.println("流程实例ID：" + groupTask.getId());
        System.out.println("流程定义ID：" + groupTask.getProcessDefinitionId());
    }

    /**
     * 完成创建任务，使其进入审批阶段
     */
    @Test
    public void finishCreate() {
        TaskService taskService = ProcessEngines.getDefaultProcessEngine()
                .getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("groupTask")
                .taskName("创建出差申请")
                .singleResult();
        taskService.complete(task.getId());
        task = taskService.createTaskQuery()
                .processDefinitionKey("groupTask")
                .taskName("经理审批")
                .singleResult();
        taskService.complete(task.getId());

    }

    /**
     * 根据候选人查询组任务
     */
    @Test
    public void findGroupTaskList() {
        // 流程定义key
        String processDefinitionKey = "groupTask";
        // 任务候选人
        String candidateUser = "lisi";
        TaskService taskService = ProcessEngines.getDefaultProcessEngine()
                .getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateUser(candidateUser)
                .list();
        for (Task task : tasks) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 拾取组任务
     */
    @Test
    public void claimTask() {
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //要拾取的任务id
        String taskId = taskService.createTaskQuery()
                .taskName("总经理审批")
                .processDefinitionKey("groupTask")
                .singleResult().getId();
        //任务候选人
        String candidateUser = "wangwu";
        //拾取任务
        //即使该用户不是候选人也能拾取(建议拾取时校验是否有资格)
        //校验该用户有没有拾取任务的资格
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(candidateUser)//根据候选人查询
                .singleResult();
        if (task != null) {
            //拾取任务
            taskService.claim(taskId, candidateUser);
            System.out.println("任务拾取成功");
        }

    }

    /**
     * 个人任务查询
     */
    @Test
    public void findPersonalTaskList() {
        // 流程定义key
        String processDefinitionKey = "groupTask";
        // 任务负责人
        String assignee = "lisi";
        TaskService taskService = ProcessEngines.getDefaultProcessEngine()
                .getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskAssignee(assignee)
                .list();
        for (Task task : tasks) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 归还组任务
     */
    @Test
    public void setAssigneeToGroupTask() {
        //  获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 查询任务使用TaskService
        TaskService taskService = processEngine.getTaskService();
        // 当前待办任务
        String processDefinitionKey = "groupTask";
        // 任务负责人
        String userId = "wangwu";
        // 校验userId是否是taskId的负责人，如果是负责人才可以归还组任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskAssignee(userId)
                .singleResult();
        if (task != null) {
            // 如果设置为null，归还组任务,该 任务没有负责人
            taskService.setAssignee(task.getId(), "lisi");
        }
    }

    /**
     * 删除部署信息
     */
    @Test
    public void deleteDeployment() {
        String key = "groupTask";
        // 根据key 得到部署id
        Deployment deployment = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeploymentQuery()
                .processDefinitionKey(key)
                .singleResult();
        if (deployment != null) {
            String deploymentId = deployment.getId();
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            // 通过流程引擎获取repositoryService
            RepositoryService repositoryService = processEngine
                    .getRepositoryService();
            //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
            repositoryService.deleteDeployment(deploymentId, true);
        }

    }
}
