package cn.liaozhi.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.liaozhi.workflow.service.IProcessOperateService;

@Service
public class ProcessOperateService implements IProcessOperateService {
   
	private Logger log = Logger.getLogger(ProcessOperateService.class);

	@Autowired
	private RuntimeService runtimeService;
	@Override
	public void createProcessInstance(String processDefinitionName, String businessKey, Map<String, Object> variables) {
		log.info("start processInstance Id ================="+runtimeService.startProcessInstanceByKey(processDefinitionName, businessKey, variables).getProcessInstanceId());
		
	}

}
