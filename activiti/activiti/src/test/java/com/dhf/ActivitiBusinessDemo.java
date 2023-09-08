package com.dhf;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/08/31 16:48
 */
public class ActivitiBusinessDemo {
    /**
     * 添加业务 key 到 activiti表
     * 在启动流程事 将 business添加到 act_ru_execution表中
     */
    @Test
    public void addBusinessKey() {
        // 1、获取 ProcessEngines
        // 2、获取 runtimeService
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService();
        // 3、启动流程实例过程中，添加 businessKey
        // startProcessInstanceByKey(String processDefinitionKey, String businessKey)
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myEvection", "1001");
        System.out.println(processInstance.getBusinessKey());
    }

    /**
     * 全部流程实例挂起与激活
     */
    @Test
    public void suspendAllProcessInstance() {
        // 1、获取 ProcessEngines
        // 2、获取 repositoryService
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService();
        // 3、查询流程定义，获取流程定义的查询对象
        // 4、获取流程定义的key，查询流程定义
        List<ProcessDefinition> myEvection = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .list();
        // 5、获取流程定义的部署id
        for (ProcessDefinition processDefinition : myEvection) {
            // 是否是暂停状态
            String processDefinitionId = processDefinition.getId();
            if (processDefinition.isSuspended()) {
                // 如果是暂停，可以执行激活操作 ,参数1 ：流程定义id ，参数2：是否激活，参数3：激活时间
                repositoryService.activateProcessDefinitionById(processDefinitionId,
                        true,
                        null
                );
                System.out.println("流程定义：" + processDefinitionId + ",已激活");
            } else {
                // 如果是激活状态，可以暂停，参数1 ：流程定义id ，参数2：是否暂停，参数3：暂停时间
                repositoryService.suspendProcessDefinitionById(processDefinitionId,
                        true,
                        null);
                System.out.println("流程定义：" + processDefinitionId + ",已挂起");
            }

            // 6、挂起流程定义
            //repositoryService.suspendProcessDefinitionById(processDefinition.getId());
            // 7、激活流程定义
            //repositoryService.activateProcessDefinitionById(processDefinition.getId());
        }
    }

    /**
     * 挂起个人流程实例
     */
    @Test
    public void suspendSingleProcessInstance() {
        RuntimeService runtimeService = ProcessEngines
                .getDefaultProcessEngine()
                .getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("10001")
                .singleResult();
        // 得到当前流程定义的实例是否都为暂停状态
        boolean suspended = processInstance.isSuspended();
        // 流程定义id
        String processDefinitionId = processInstance.getId();
        // 判断是否为暂停
        if (suspended) {
        // 如果是暂停，可以执行激活操作 ,参数：流程定义id
            runtimeService.activateProcessInstanceById(processDefinitionId);
            System.out.println("流程定义：" + processDefinitionId + ",已激活");
        } else {
        // 如果是激活状态，可以暂停，参数：流程定义id
            runtimeService.suspendProcessInstanceById(processDefinitionId);
            System.out.println("流程定义：" + processDefinitionId + ",已挂起");
        }

    }
}
