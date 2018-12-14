package cn.liaozhi.workflow.ext.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.task.Task;

import cn.liaozhi.workflow.vo.FunctionListeneerVO;


/**
 * @author cKF69083
 * 
 *         工作流执行命令接口
 * 
 *         主要是为了获取工作流的执行上下文
 */
public interface IWorkflowCommandService {

	/**
	 * @param command
	 *            执行的命令
	 * @return
	 *         返回执行结果
	 * 
	 *         执行command
	 */
	<T> T executeCommand(Command<T> command);
	
	/**
	 * 自动让流程往下走，系统层面自动将其往下走，在任务回收的时候用到
	 * @param taskId
	 * 
	 */
	void autoCompleteTask(String taskId,Map<String, Object> variables);
	
	/**
	 * 完成任务，不触发任务完成监听器	
	 * @author lWX80565
	 * @since 2014-4-23
	 * @param taskId
	 * @param variables
	 */
	void completeTaskWithOutFireEvent(String taskId,Map<String, Object> variables);
	
	/**
	 * @param taskId
	 * 
	 * 删除 任务的所有候选处理人和处理组
	 */
	void deleteCandidateForTask(String taskId);
	
	/**
	 * @param taskId
	 * 
	 * 删除 任务的所有转审类型的处理人和组
	 */
	void deleteTransferForTask(String taskId);
	
	/**
	 * @param id
	 * @return
	 * 
	 * 根据流程id检测流程定义是否挂起
	 */
	boolean getProcessDefinitionSuspendById(String id);
	
	
	/**
	 * @param id
	 * @return
	 * 
	 * 根据流程key检测流程定义是否挂起
	 */
	boolean getProcessDefinitionSuspendByKey(String id);
	
	/**
	 * @param id
	 * @return
	 * 
	 * 根据流程定义ID获取流程定义对象
	 */
	ReadOnlyProcessDefinition getReadOnlyProcessDefinitionById(String id);
	
	
	/**
	 * @param Key
	 * @return
	 * 
	 * 根据流程定义Key获取最新版本流程定义对象
	 */
	ReadOnlyProcessDefinition getReadOnlyProcessDefinitionByKey(String Key);
	
	
	/** 触发功能事件监听器
	 * @author z00174662
	 * @since 2012-12-20
	 * @param functionListeners
	 * @param task
	 */
	void notifyFunctionListener(List<FunctionListeneerVO> functionListeners, Task task, Map<String, String> args);
	
}
