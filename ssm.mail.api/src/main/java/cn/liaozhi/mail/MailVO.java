package cn.liaozhi.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.liaozhi.core.base.KeyValuePairVO;

/**
 * MailVO.java
 * @author ZHIYUN
 *
 */
public class MailVO implements Serializable {

	private static final long serialVersionUID = -4768150827992665086L;
	/**
	 * 主送人  元素是主送人邮件地址
	 */
	private String[] to;
	/**
	 * 抄送人  元素是抄送人邮件地址
	 */
	private String[] cc;
	/**
	 * 密送人 元素是密送人邮件地址
	 */
	private String[] bcc;	
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容   String类型的固定格式内容
	 */
	private String content;
	
	/**
	 * 邮件模板名称 
	 * 模板在系统的“富文本”  类型是“MailTemplate”下定义的名称
	 */
	private String templateName;
	private String useBean;
	private int sendCount;
	
	/**
	 * 邮件发送人的邮件地址
	 */
	private String addresser;
	
	/**
	 * 邮件发件人的名字
	 */
	private String fromName;
	
	/**
	 * 用于Velocity的绑定对象的名字和对象的Mapping
	 */
	private Map<String,Serializable> bindItems = new HashMap<String,Serializable>();
	/**
	 * 文件名和路径的Mapping
	 */
	private Map<String,String> attachments = new HashMap<String,String>();
	
	/**
	 * 文件名和路径的Mapping,可用来添加同名文件
	 */
	private List<KeyValuePairVO> attachmentList = new ArrayList<KeyValuePairVO>();
	
	/**
	 * 邮件重要性属性[1=High|0=Normal|5=Low]
	 */
	private int priority;
	
	/**邮件属性key */
	private String headerKey;
	
	/**邮件属性Value */
	private String headerValue;
	
	/**
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}
	/**
	 * @return the cc
	 */
	public String[] getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	/**
	 * @return the bcc
	 */
	public String[] getBcc() {
		return bcc;
	}
	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	/**
	 * @return the addresser
	 */
	public String getAddresser() {
		return addresser;
	}
	/**
	 * @param addresser the addresser to set
	 */
	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}
	
	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}
	/**
	 * @param fromName the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
 
	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * @return the bindItems
	 */
	public Map<String, Serializable> getBindItems() {
		return bindItems;
	}
	/**
	 * @param bindItems the bindItems to set
	 */
	public void setBindItems(Map<String, Serializable> bindItems) {
		this.bindItems = bindItems;
	}
	/**
	 * @return the attachments
	 */
	public Map<String, String> getAttachments() {
		return attachments;
	}
	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}
	/**
	 * @return the useBean
	 */
	public String getUseBean() {
		return useBean;
	}
	/**
	 * @param useBean the useBean to set
	 */
	public void setUseBean(String useBean) {
		this.useBean = useBean;
	}
	
	public void addCount(){
		this.sendCount++;
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public List<KeyValuePairVO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<KeyValuePairVO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the headerKey
	 */
	public String getHeaderKey() {
		return headerKey;
	}
	/**
	 * @param headerKey the headerKey to set
	 */
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}
	/**
	 * @return the headerValue
	 */
	public String getHeaderValue() {
		return headerValue;
	}
	/**
	 * @param headerValue the headerValue to set
	 */
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
}
