package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.CustomerQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 客户
 * @author victorhan
 *
 */
public class CustomerDO extends EntityDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1552320202480467338L;
	
	private String name;	//称呼
	private String mobile;	//电话号码
	private String password;//密码
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public CustomerQueryCondition toQueryCondition(){
		CustomerQueryCondition queryCondition = new CustomerQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
