package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class PurchaseQueryCondition extends QueryCondition {
	
	public PurchaseQueryCondition setName(String name){
		put("name", name);
		return this;
	}
	
	public String getName(){
		return getString("name");
	}
	
	public PurchaseQueryCondition setCustomerId(Long customerId) {
		put("customerId",customerId);
		return this;
	}
	
	public Long getCutomerId(){
		return getLong("customerId");
	}
	
	public PurchaseQueryCondition setCustomerName(Long customerName) {
		put("customerName",customerName);
		return this;
	}
	
	public Long getCutomerName(){
		return getLong("customerName");
	}
	
	public PurchaseQueryCondition setProdType(String prodType){
		put("prodType",prodType);
		return this;
	}
	
	public String getProdType(){
		return getString("prodType");
	}
	
	public PurchaseQueryCondition setExtendId(Long extendId) {
		put("extendId",extendId);
		return this;
	}
	
	public Long getExtendId(){
		return getLong("extendId");
	}
	
	public PurchaseQueryCondition setExtendCode(String extendCode) {
		put("extendCode",extendCode);
		return this;
	}
	
	public String getExtendCode(){
		return getString("extendCode");
	}
	
	public PurchaseQueryCondition setOrderId(Long orderId) {
		put("orderId",orderId);
		return this;
	}
	
	public Long getOrderId(){
		return getLong("orderId");
	}
	
	public void setCreateStart(String createStart){
		put("createStart",createStart);
		setGmtCreateStart(StringToDate(createStart));
	}
	
	public String getCreateStart(){
		return getString("createStart");
	}
	
	public void setCreateEnd(String createEnd){
		put("createEnd",createEnd);
		setGmtCreateEnd(StringToDate(createEnd));
	}
	
	public String getCreateEnd(){
		return getString("createEnd");
	}

	public PurchaseQueryCondition setGmtCreateStart(Date from){
		put("gmtCreateStart", DateTools.getDayBegin(from));
		return this;
	}
	
	public Date getGmtCreateStart(){
		return getDate("gmtCreateStart");
	}
	
	public PurchaseQueryCondition setGmtCreateEnd(Date to){
		put("gmtCreateEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	public Date getGmtCreateEnd(){
		return getDate("gmtCreateEnd");
	}
	
	@Override
	public PurchaseQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public PurchaseQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public PurchaseQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public PurchaseQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
