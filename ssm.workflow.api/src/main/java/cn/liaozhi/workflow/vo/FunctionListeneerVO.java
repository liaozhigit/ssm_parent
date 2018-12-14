package cn.liaozhi.workflow.vo;

import java.io.Serializable;

import cn.liaozhi.workflow.ext.delegate.IFunctionListener;

/**
 * 功能事件处理类VO
 * @author z00174662
 * @since 2012-11-4
 */
public class FunctionListeneerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7357451710779685613L;

	/**
	 * before或者after的事件
	 */
	private String eventName;
	
	/**
	 * 功能事件
	 */
	private IFunctionListener listener;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public IFunctionListener getListener() {
		return listener;
	}

	public void setListener(IFunctionListener listener) {
		this.listener = listener;
	}

}
