package com.dhf;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/08/30 11:32
 */
public class ActivitiDemo {
    /**
     * 测试流程部署
     */
    @Test
    public void test() {
        // 1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        // 2、获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、使用service进行流程部署，定义一个流程的名字，把bpmn和png部署到数据库中
        Deployment deployment = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/evection.bpmn20.xml")
                .addClasspathResource("png/evection.png")
                // 禁止文件校验
                // org.activiti.bpmn.exceptions.XMLException: cvc-complex-type.2.4.a: 发现了以元素 'incoming' 开头的无效内容
                .disableSchemaValidation()
                .deploy();
        // 4、输出部署信息
        System.out.printf("流程部署id: %s\n", deployment.getId());
        System.out.println("流程部署名称: " + deployment.getName());
    }

    /**
     * 压缩包部署
     */
    @Test
    public void deployProcessByZip() {
        // 定义zip输入流
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                        "zip/evection.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 获取repositoryService
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        // 流程部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 启动流程实例
     * <p>
     * act_hi_actinst     流程实例执行历史
     * act_hi_identitylink  流程的参与用户历史信息
     * act_hi_procinst      流程实例历史信息
     * act_hi_taskinst       流程任务历史信息
     * act_ru_execution   流程执行信息
     * act_ru_identitylink  流程的参与用户信息
     * act_ru_task              任务信息
     */
    @Test
    public void startProcess() {
        // 1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、根据流程定义 id 启动流程 act_re_procdef 表中
        ProcessInstance evection = runtimeService
                .startProcessInstanceByKey("myEvection");
        //流程定义id: evection:1:4  act_re_procdef 表中
        //流程实例id: 2501 act_ru_execution 表中
        //当前活动id: null
        System.out.printf("流程定义id: %s\n", evection.getProcessDefinitionId());
        System.out.printf("流程实例id: %s\n", evection.getId());
        System.out.printf("当前活动id: %s\n", evection.getActivityId());

    }

    /**
     * 查询用户待执行的任务
     */
    @Test
    public void searchTaskListByUser() {
        // 1、获取流程引擎
        // 2、获取taskService
        // 3、根据流程 key 和 任务负责任人 查询任务
        TaskService taskService = ProcessEngines.getDefaultProcessEngine().getTaskService();
        List<Task> list = taskService.createTaskQuery()
                // 流程的 key
                .processDefinitionKey("myEvection")
                // 任务负责人
                .taskAssignee("zhangsan")
                .list();
        for (Task task : list) {
            System.out.println("流程示例id=" + task.getProcessDefinitionId());
            System.out.println("任务id=" + task.getId());
            System.out.println("任务负责人=" + task.getAssignee());
            System.out.println("任务名称=" + task.getName());
        }
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask() {
        // 1、获取流程引擎
        // 2、获取TaskService
        TaskService taskService = ProcessEngines.getDefaultProcessEngine().getTaskService();
        // 3、根据id完成任务
        //taskService.complete("2505");
        Task task;
        while ((task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                //.taskAssignee("jerry")
                .singleResult()) != null) {
            taskService.complete(task.getId());
        }


    }

    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition() {
        // 1、获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、得到ProcessDefinitionQuery 对象
        // 4、查询出当前所有的流程定义
        List<ProcessDefinition> definitionList = repositoryService.createProcessDefinitionQuery()
                // 条件：processDefinitionKey =evection
                .processDefinitionKey("myEvection")
                // orderByProcessDefinitionVersion 按照版本排序
                .orderByProcessDefinitionVersion()
                // desc倒叙
                .desc()
                // list 返回集合
                .list();
        // 输出流程定义信息
        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义 id=" + processDefinition.getId());
            System.out.println("流程定义 name=" + processDefinition.getName());
            System.out.println("流程定义 key=" + processDefinition.getKey());
            System.out.println("流程定义 Version=" + processDefinition.getVersion());
            System.out.println("流程部署ID =" + processDefinition.getDeploymentId());
        }
    }

    /**
     * 流程删除
     * 使用repositoryService删除流程定义，历史表信息不会被删除
     * <p>
     * act_ge_bytearray
     * act_re_deployment
     * act_re_procdef
     * <p>
     * 如果当前流程有任务没有完成，需要使用级联删除
     * repositoryService.deleteDeployment(deploymentId, true);
     */
    @Test
    public void deleteDeployment() {
        // 流程部署id
        String deploymentId = "1";

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        //repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 流程资源下载
     * 1、使用activiti提供的api下载资源文件 RepositoryService
     * 2、自己从数据库中下载文件
     */
    public void getDeployment() {
        RepositoryService repositoryService = ProcessEngines
                .getDefaultProcessEngine()
                .getRepositoryService();
        /*
         *  table act_ge_bytearray
         *       | id | name | ... | deployment_id_ | bytes | ... |
         *
         * */
        // 获取流程定义部署 id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        String deploymentId = processDefinition.getDeploymentId();
        // 通过部署id 读取资源信息
        // 获取 png 图片流
        // 流程定义表(act_re_procdef) 中获取 png 图片目录
        String diagramResourceName = processDefinition.getDiagramResourceName();
        // 通过 部署id 和 资源名称 获取资源
        InputStream pngInputStream = repositoryService
                .getResourceAsStream(deploymentId, diagramResourceName);
        String resourceName = processDefinition.getResourceName();
        InputStream bpmnInputStream = repositoryService
                .getResourceAsStream(deploymentId, resourceName);
        // 构造OutputStream
        File pngFile = new File("d/evection.png");
        File bpmnFile = new File("d/evection.bpmn");
        try (FileOutputStream pngFileOut = new FileOutputStream(pngFile);
             FileOutputStream bpmnFileOut = new FileOutputStream(bpmnFile)) {
            IOUtils.copy(pngInputStream, pngFileOut);
            IOUtils.copy(bpmnInputStream, bpmnFileOut);
            // 关闭流
            pngInputStream.close();
            bpmnInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 流程历史信息的查看
     */
    @Test
    public void findHistoryInfo(){
        // 1、获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 historyService
        HistoryService historyService = processEngine.getHistoryService();
        // 3、查询 act_hi_actinst
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                // 流程 id
                //.processDefinitionId("myEvection:1:4")
                .processInstanceId("2501")
                // 根据开始时间升序(asc)排序
                .orderByHistoricActivityInstanceStartTime().asc()
                .list();
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("<======================================>");
        }
    }
}
