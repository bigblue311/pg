package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.PurchaseQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 订单信息
 * @author victorhan
 *
 */
public class PurchaseDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1814746324766240895L;
	private Long 	customerId;		//客户ID
	private String 	customerName;	//客户姓名
	private String 	prodType;		//产品类型
	private Long 	extendId;		//外部ID
	private String 	extendCode;		//外部编号
	private Integer quantity;		//数量
	private String 	unit;			//单位
	private Double  price;			//价格
	private Long 	orderId;		//订单编号
	private String 	name;			//产品名称
	private String 	title;			//产品标题
	private String 	comment;		//备注
	
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

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PurchaseQueryCondition toQueryCondition(){
		PurchaseQueryCondition queryCondition = new PurchaseQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
