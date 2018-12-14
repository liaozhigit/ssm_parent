package cn.liaozhi.core.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 键值对VO
 * @author liaozhi
 *2018年11月18日下午10:38:40
 */
@XmlRootElement
public class KeyValuePairVO implements Serializable {
	
	private static final long serialVersionUID = -4667685944202222910L;
	private String key;
	private String value;
	
	/**
	 * 默认构造
	 */
	public KeyValuePairVO(){
		
	}
	/**
	 * 通过Key和Value构造
	 * @param key
	 * @param value
	 */
	public KeyValuePairVO(String key,String value){
		this.key = key;
		this.value = value;
	}
	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key 要设置的 key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value 要设置的 value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
		
}
