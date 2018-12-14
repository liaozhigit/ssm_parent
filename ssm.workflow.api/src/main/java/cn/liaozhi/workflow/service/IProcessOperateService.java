package cn.liaozhi.workflow.service;

import java.util.Map;

/**
 * 流程操作服务类
 * @author ZHIYUN
 *
 */
public interface IProcessOperateService {

	
	/**
	 * 新建流程，传入业务主键，一些流程参数到流程
	 * @param processDefinitionName
	 * @param businessKey
	 * @param variables
	 */
	public void createProcessInstance(String processDefinitionName, String businessKey, Map<String, Object> variables);

}
