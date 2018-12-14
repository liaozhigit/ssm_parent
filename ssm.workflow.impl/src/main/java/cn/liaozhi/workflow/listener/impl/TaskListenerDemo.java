/**
 * 
 */
package cn.liaozhi.workflow.listener.impl;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 任务拦截demo例子,可以在任务前后做一些业务逻辑
 * 
 * @author z00174662
 *
 */
@Service
public class TaskListenerDemo implements TaskListener {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(TaskListenerDemo.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.
	 * delegate.DelegateTask)
	 */
	public void notify(DelegateTask delegateTask) {
		try {
			log.info(delegateTask.getAssignee() + "-----------------------------Assignee");
			Thread.sleep(5000);
			String reg = (String) delegateTask.getVariable("reg");
			log.info(delegateTask.getId() + "-----------------------------taskId");
			log.info(delegateTask.getProcessInstanceId() + "-----------------------------ProcessInstanceId");
			log.info(reg + "-----------------------------reg   Variable");
			log.info("execute ServiceTask2Delegate Thread ======" + Thread.currentThread().getName());
			log.info("TaskListenerDemo reg   Variable=========" + reg);
			log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
