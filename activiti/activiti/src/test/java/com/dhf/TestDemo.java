package com.dhf;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/08/29 18:58
 */
public class TestDemo {
    /**
     * 使用activiti默认方式创建mysql tables
     */
    @Test
    public void test() {
        // getDefaultProcessEngine()会自动读取activiti.cfg.xml文件
        /*
        * getDefaultProcessEngine() ->
        * resources = classLoader.getResources("activiti.cfg.xml");
        * */
        // 一般方式创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 配置文件方式创建
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine engine = engineConfiguration.buildProcessEngine();
        // activiti资源管理服务类
        RepositoryService repositoryService = engine.getRepositoryService();
        // 流程运行管理类
        RuntimeService runtimeService = engine.getRuntimeService();
        // 任务管理类
        TaskService taskService = engine.getTaskService();
        // 历史管理类
        HistoryService historyService = engine.getHistoryService();
        // 引擎管理类
        ManagementService managementService = engine.getManagementService();
    }
}
