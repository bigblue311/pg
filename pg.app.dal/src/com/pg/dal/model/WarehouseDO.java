package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.WarehouseQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 仓库
 * @author victorhan
 *
 */
public class WarehouseDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -690583555143238541L;
	
	private String name;	//名称
	private String address;	//地址
	private String keeper;	//收件人
	private String mobile;	//手机号码
	private String phone;	//联系电话
	private Long customerId;//客户ID
	private String comment;	//备注
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public WarehouseQueryCondition toQueryCondition(){
		WarehouseQueryCondition queryCondition = new WarehouseQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
