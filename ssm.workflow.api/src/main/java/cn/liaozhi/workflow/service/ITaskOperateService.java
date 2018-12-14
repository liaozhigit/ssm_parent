package cn.liaozhi.workflow.service;

import java.util.Map;

/**
 * 任务操作服务接口
 * @author liaozhi
 *2018年12月4日下午11:10:24
 */
public interface ITaskOperateService {
	
	void complete(String taskId);
	
	void complete(String taskId, Map<String, Object> variables);
}
