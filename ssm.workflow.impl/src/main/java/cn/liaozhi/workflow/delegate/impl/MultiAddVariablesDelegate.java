package cn.liaozhi.workflow.delegate.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service
public class MultiAddVariablesDelegate implements JavaDelegate {
	private Logger log = Logger.getLogger(MultiAddVariablesDelegate.class);

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		List<String> subList = new ArrayList<String>();
		subList.add("test1");
		subList.add("test2");
		delegateExecution.setVariable("subList", subList);
		log.info("MultiAddvarblesDelegate add multi varbles success!");
		
	}

}
