package cn.liaozhi.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.liaozhi.workflow.service.ITaskOperateService;
@Service
public class TaskOperateService implements ITaskOperateService {

	@Autowired
	private TaskService taskService;
	
	
	
	@Override
	public void complete(String taskId) {
		this.complete(taskId,null);

	}

	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);

	}

}
