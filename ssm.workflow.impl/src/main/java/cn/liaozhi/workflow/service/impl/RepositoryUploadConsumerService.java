package cn.liaozhi.workflow.service.impl;

import java.io.IOException;

import org.activiti.engine.repository.Deployment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.liaozhi.workflow.service.IProcessRepositoryService;
@Service
public class RepositoryUploadConsumerService {
	@Autowired
	private IProcessRepositoryService processRepositoryService;
	
	private Logger log = Logger.getLogger(RepositoryUploadConsumerService.class);
	
	private void deployFiles() throws IOException {
		Deployment deployment = processRepositoryService.createDeployment().name("boundaryEvent入门程序")// 添加部署的名称
		.addClasspathResource("diagrams/boundaryEvent.bpmn")// 从classpath的资源中加载，一次只能加载一个文件
		.addClasspathResource("diagrams/boundaryEvent.png")// 从classpath的资源中加载，一次只能加载一个文件
		.deploy();// 完成部署
log.info("部署ID：" + deployment.getId());// 1
log.info("部署名称：" + deployment.getName());// helloworld入门程序
//
//String processDefinitionKey = "boundaryEvent";
//// 使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
//ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);
//log.info("流程实例id=" + pi.getId());
//log.info("流程定义id=" + pi.getProcessDefinitionId());
	}
}
