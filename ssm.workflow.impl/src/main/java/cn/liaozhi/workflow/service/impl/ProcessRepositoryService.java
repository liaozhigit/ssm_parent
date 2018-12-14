package cn.liaozhi.workflow.service.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.liaozhi.workflow.service.IProcessRepositoryService;
@Service
public class ProcessRepositoryService implements IProcessRepositoryService {
	@Autowired
	private RepositoryService repositoryService;
	@Override
	public DeploymentBuilder createDeployment() {
		return repositoryService.createDeployment();
	}

}
