package com.hdsong.activiti.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @作者 宋寒冬
 * @日期 2020-04-07
 * @时间 04:30
 * @描述
 */
@RestController
@Slf4j
public class ActivitiController {


    @Autowired
    ProcessEngine processEngine;
    @Autowired
    ObjectMapper objectMapper;

//    /**
////     * 创建模型
////     */
////    @RequestMapping("/create")
////    public void create(HttpServletRequest request, HttpServletResponse response) {
////        try {
////            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
////
////            RepositoryService repositoryService = processEngine.getRepositoryService();
////
////            ObjectMapper objectMapper = new ObjectMapper();
////            ObjectNode editorNode = objectMapper.createObjectNode();
////            editorNode.put("id", "canvas");
////            editorNode.put("resourceId", "canvas");
////            ObjectNode stencilSetNode = objectMapper.createObjectNode();
////            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
////            editorNode.put("stencilset", stencilSetNode);
////            Model modelData = repositoryService.newModel();
////
////            ObjectNode modelObjectNode = objectMapper.createObjectNode();
////            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "hello1111");
////            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
////            String description = "hello1111";
////            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
////            modelData.setMetaInfo(modelObjectNode.toString());
////            modelData.setName("hello1111");
////            modelData.setKey("12313123");
////
////            //保存模型
////            repositoryService.saveModel(modelData);
////            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
////            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
////        } catch (Exception e) {
////            System.out.println("创建模型失败：");
////        }
////    }

    /**
     * 新建一个空模型
     */
    @RequestMapping("/create")
    public void newModel(HttpServletResponse response) throws IOException, IOException {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        response.sendRedirect("/modeler.html?modelId="+id);
    }


    /**
     * 获取所有模型
     */
    @RequestMapping("/modelList")
    @ResponseBody
    public Object modelList(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return repositoryService.createModelQuery().list();
    }

    /**
     * 发布模型为流程定义
     */
    @RequestMapping("/deploy")
    @ResponseBody
    public Object deploy(String modelId) throws Exception {

        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size()==0){
            return "数据模型不符要求，请至少设计一条主线流程。";
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return "SUCCESS";
    }

    /**
     *  启动流程
     */
    @RequestMapping("/start")
    @ResponseBody
    public Object startProcess(String keyName) {
        ProcessInstance process = processEngine.getRuntimeService().startProcessInstanceByKey(keyName);

        return process.getId() + " : " + process.getProcessDefinitionId();
    }

    /**
     *  提交任务
     */
    @RequestMapping("/run")
    @ResponseBody
    public Object run(String processInstanceId) {
        Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();

        log.info("task {} find ", task.getId());
        processEngine.getTaskService().complete(task.getId());
        return "SUCCESS";
    }

}
