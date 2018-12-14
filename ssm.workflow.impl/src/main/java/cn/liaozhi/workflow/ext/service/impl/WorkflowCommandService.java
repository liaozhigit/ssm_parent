package cn.liaozhi.workflow.ext.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.task.Task;

import cn.liaozhi.workflow.ext.service.IWorkflowCommandService;
import cn.liaozhi.workflow.vo.FunctionListeneerVO;

/**
 * @author cKF69083
 * 
 * 
 *         IWorkflowCommandService实现,提供命令执行
 */
public class WorkflowCommandService extends ServiceImpl implements IWorkflowCommandService {

	@Override
	public <T> T executeCommand(Command<T> command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void autoCompleteTask(String taskId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeTaskWithOutFireEvent(String taskId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCandidateForTask(String taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransferForTask(String taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getProcessDefinitionSuspendById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getProcessDefinitionSuspendByKey(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReadOnlyProcessDefinition getReadOnlyProcessDefinitionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadOnlyProcessDefinition getReadOnlyProcessDefinitionByKey(String Key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyFunctionListener(List<FunctionListeneerVO> functionListeners, Task task,
			Map<String, String> args) {
		// TODO Auto-generated method stub
		
	}

	
}
