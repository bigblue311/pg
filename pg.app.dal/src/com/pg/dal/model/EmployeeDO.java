package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.EmployeeQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 雇员
 * @author victorhan
 *
 */
public class EmployeeDO extends EntityDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8273412996658721011L;
	
	private String name;			//登录名
	private String password; 		//密码
	private String role;			//角色
	private String enable;			//有效
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public EmployeeQueryCondition toQueryCondition(){
		EmployeeQueryCondition queryCondition = new EmployeeQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
