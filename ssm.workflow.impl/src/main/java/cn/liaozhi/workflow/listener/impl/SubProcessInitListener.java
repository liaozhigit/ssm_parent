package cn.liaozhi.workflow.listener.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.liaozhi.workflow.delegate.impl.MultiAddVariablesDelegate;
@Service
public class SubProcessInitListener implements ExecutionListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(MultiAddVariablesDelegate.class);

	@Override
	public void notify(DelegateExecution delegateExecution) {
		List<String> subList = new ArrayList<String>();
		subList.add("test1");
		subList.add("test2");
		delegateExecution.setVariable("subList", subList);
		log.info("SubProcessInitListener add multi varbles success!");
	}

}
