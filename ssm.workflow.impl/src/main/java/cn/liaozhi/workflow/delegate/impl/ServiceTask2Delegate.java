package cn.liaozhi.workflow.delegate.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ServiceTask2Delegate implements JavaDelegate {
	private Logger log = Logger.getLogger(ServiceTask2Delegate.class);

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Thread.sleep(5000);
		String reg = (String) delegateExecution.getVariable("reg");
		log.info("execute ServiceTask2Delegate Thread ======" + Thread.currentThread().getName());
		log.info("ServiceTask2Delegate execute=========" + reg);
		log.info("variavles=" + delegateExecution.getVariables());
		delegateExecution.setVariable("serviceTask2", "I am serviceTask2");
		log.info("I am serviceTask2.");

	}

}
