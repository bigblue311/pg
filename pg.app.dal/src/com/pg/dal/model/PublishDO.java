package com.pg.dal.model;

import java.io.Serializable;
import java.util.Date;

import com.pg.dal.enumerate.EnableEnum;
import com.pg.dal.query.PublishQueryCondition;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.common.tools.StringTools;
import com.victor.framework.dal.basic.EntityDO;

public class PublishDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 308922163884509394L;
	private Double price;		//单价
	private String unit;		//单位
	private Long balance;		//库存
	private Long warehouseId;	//仓库ID
	private String img;			//图片
	private String description;	//描述
	private String prodType;	//外部类型(商品/商品包)
	private Long extendId;		//外部ID(商品/商品包)
	private String extendCode;	//外部编码(商品/商品包)
	private String 	name;		//产品名称
	private String 	title;		//产品标题
	private Long limitBuy;		//最小预定量
	private Date validFrom;		//有效期开始
	private Date validTo;		//有效期结束
	private String enable;		//有效
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getLimitBuy() {
		return limitBuy;
	}
	public void setLimitBuy(Long limitBuy) {
		this.limitBuy = limitBuy;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public boolean isValid(){
		if(StringTools.isEmpty(enable)){
			return false;
		}
		if(EnableEnum.无效.getCode().equals(enable)){
			return false;
		}
		if(validFrom != null && DateTools.today().before(validFrom)){
			return false;
		}
		if(validTo != null && DateTools.today().after(validTo)){
			return false;
		}
		return true;
	}
	
	public PublishQueryCondition toQueryCondition(){
		PublishQueryCondition queryCondition = new PublishQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
