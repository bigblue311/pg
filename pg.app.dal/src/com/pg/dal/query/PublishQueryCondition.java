package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class PublishQueryCondition extends QueryCondition {
	
	public PublishQueryCondition setWarehouseId(Long warehouseId) {
		put("warehouseId",warehouseId);
		return this;
	}
	
	public Long getWarehouseId(){
		return getLong("warehouseId");
	}
	
	public PublishQueryCondition setProdType(String prodType) {
		put("prodType",prodType);
		return this;
	}
	
	public String getProdType(){
		return getString("prodType");
	}
	
	public PublishQueryCondition setExtendId(Long extendId) {
		put("extendId",extendId);
		return this;
	}
	
	public Long getExtendId(){
		return getLong("extendId");
	}
	
	public PublishQueryCondition setExtendCode(String extendCode) {
		put("extendCode",extendCode);
		return this;
	}
	
	public String getExtendCode(){
		return getString("extendCode");
	}
	
	public PublishQueryCondition setValid(Boolean valid) {
		put("valid",valid);
		return this;
	}
	
	public Boolean getValid(){
		return getBoolean("valid");
	}
	
	public PublishQueryCondition setOrderBy(String orderBy){
		put("orderBy",orderBy);
		return this;
	}
	
	public String getOrderBy(){
		return getString("orderBy");
	}
	
	public PublishQueryCondition setOrder(String order){
		put("order",order);
		return this;
	}
	
	public String getOrder(){
		return getString("order");
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

	public PublishQueryCondition setGmtCreateStart(Date from){
		put("gmtCreateStart", DateTools.getDayBegin(from));
		return this;
	}
	
	public Date getGmtCreateStart(){
		return getDate("gmtCreateStart");
	}
	
	public PublishQueryCondition setGmtCreateEnd(Date to){
		put("gmtCreateEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	public Date getGmtCreateEnd(){
		return getDate("gmtCreateEnd");
	}
	
	@Override
	public PublishQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public PublishQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public PublishQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public PublishQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
