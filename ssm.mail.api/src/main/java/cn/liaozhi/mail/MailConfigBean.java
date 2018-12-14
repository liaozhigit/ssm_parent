package cn.liaozhi.mail;

import java.io.Serializable;

/**
 * Mail 配置Bean	
 * @author l54883
 * @since 2011-11-21
 */
public class MailConfigBean implements Serializable {
	
	private static final long serialVersionUID = -5958955475082908702L;
	
	private String fromAddress;
	private String fromName;
	private String templateHtmlCategory;
	private String systemAdminAddress;
	
	
	public String getSystemAdminAddress() {
		return systemAdminAddress;
	}
	public void setSystemAdminAddress(String systemAdminAddress) {
		this.systemAdminAddress = systemAdminAddress;
	}
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
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
	 * @return the templateHtmlCategory
	 */
	public String getTemplateHtmlCategory() {
		return templateHtmlCategory;
	}
	/**
	 * @param templateHtmlCategory the templateHtmlCategory to set
	 */
	public void setTemplateHtmlCategory(String templateHtmlCategory) {
		this.templateHtmlCategory = templateHtmlCategory;
	}
	
}
