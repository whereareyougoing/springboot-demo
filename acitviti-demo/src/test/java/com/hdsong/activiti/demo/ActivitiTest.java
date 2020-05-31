package com.hdsong.activiti.demo;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者 宋寒冬
 * @日期 2020-04-07
 * @时间 01:43
 * @描述
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcitvitiDemoApplication.class)
public class ActivitiTest extends AcitvitiDemoApplicationTest{


    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**使用代码创建工作流需要的23张表*/
    @Test
    public void createTable(){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://192.168.116.10:3306/db_activiti?useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("123456");

        /**
         public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
         public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表再创建表
         public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
         */
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }

    @Test
    public void createModel(){

        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://192.168.116.10:3306/db_activiti?useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("123456");


        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();


        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment().name("数据资产部BI平台组二级审批流程模型test").addClasspathResource("bpmn/second_approve1.bpmn").deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    @Test
    public void bpmnModel(){
        BpmnModel bpmnModel = new BpmnModel();

    }

}
