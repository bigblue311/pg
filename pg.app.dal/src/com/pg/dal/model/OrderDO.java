package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.OrderQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 订单信息
 * @author victorhan
 *
 */
public class OrderDO extends EntityDO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6072027456711318024L;
	private String addressFrom;		//发货仓库
	private String addressTo;		//收货仓库
	private String keeper;			//收货联系人
	private String phone;			//收货联系人电话
	private String mobile;			//收货联系人手机
	private Long customerId;		//客户ID
	private String customerName;	//客户姓名
	private Double deposit;			//定金
	private Double totalPrice;		//总价(不含运费)
	private Double transportFee;	//运费
	private String transportCode;	//物流编号
	private String status;			//状态
	private String comment;			//备注
	
	public String getAddressFrom() {
		return addressFrom;
	}
	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}
	public String getAddressTo() {
		return addressTo;
	}
	public void setAddressTo(String addressTo) {
		this.addressTo = addressTo;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(Double transportFee) {
		this.transportFee = transportFee;
	}
	public String getTransportCode() {
		return transportCode;
	}
	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public OrderQueryCondition toQueryCondition(){
		OrderQueryCondition queryCondition = new OrderQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
