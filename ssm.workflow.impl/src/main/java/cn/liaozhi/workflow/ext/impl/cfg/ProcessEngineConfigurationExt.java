package cn.liaozhi.workflow.ext.impl.cfg;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.liaozhi.workflow.ext.service.IWorkflowCommandService;
import cn.liaozhi.workflow.ext.service.impl.WorkflowCommandService;

/**
 * 扩展的activiti engine配置类
 * @author ZHIYUN
 *
 */
public class ProcessEngineConfigurationExt extends SpringProcessEngineConfiguration implements ApplicationContextAware {

	/**
	 * 扩展的命令执行器
	 */
	protected IWorkflowCommandService workflowCommandService = new WorkflowCommandService();
    
	protected ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
