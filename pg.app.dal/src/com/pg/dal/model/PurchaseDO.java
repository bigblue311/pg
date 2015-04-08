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
	private String 	addressFrom;	//发货仓库
	private String 	addressTo;		//收货仓库
	private String 	keeper;			//收货联系人
	private String 	phone;			//收货联系人电话
	private String 	mobile;			//收货联系人手机
	private Double 	transportFee;	//运费
	private String 	transportCode;	//物流编号
	private Long 	publishId;		//商品ID
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
	
	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
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

	public PurchaseQueryCondition toQueryCondition(){
		PurchaseQueryCondition queryCondition = new PurchaseQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
