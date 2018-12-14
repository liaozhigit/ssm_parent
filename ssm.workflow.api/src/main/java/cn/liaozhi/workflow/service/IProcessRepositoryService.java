package cn.liaozhi.workflow.service;

import org.activiti.engine.repository.DeploymentBuilder;

public interface IProcessRepositoryService {

	
	DeploymentBuilder createDeployment();
}
