package cn.liaozhi.workflow.ext.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;

/**
 * 功能事件前后添加的监听器
 * @author ZHIYUN
 *
 */
public interface IFunctionListener {
	
	/**
	 * 功能事件执行之前
	 */
	String EVENTNAME_BEFORE = "before";
	/**
	 * 功能事件执行之后
	 */
	String EVENTNAME_AFTER = "after";

	/**
	 * 默认执行的方法
	 * @param delegateTask
	 */
	void notify(DelegateTask delegateTask);
	
	/**
	 * 执行触发	
	 * @param delegateExecution
	 */
	void notify(DelegateExecution delegateExecution);
}
