package com.pg.dal.model;

import java.io.Serializable;

import com.victor.framework.dal.basic.EntityDO;

/**
 * 系统配置
 * @author victorhan
 *
 */
public class SystemConfigDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 366539580312188554L;
	private String key; 	//key
	private String value;	//value
	private String desc;	//描述
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
