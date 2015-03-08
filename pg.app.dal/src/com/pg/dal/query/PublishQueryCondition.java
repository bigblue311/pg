package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class PublishQueryCondition extends QueryCondition {
	
	public PublishQueryCondition setWarehouseId(Long warehouseId) {
		put("warehouseId",warehouseId);
		return this;
	}
	
	public PublishQueryCondition setProdType(String prodType) {
		put("prodType",prodType);
		return this;
	}
	
	public PublishQueryCondition setExtendId(Long extendId) {
		put("extendId",extendId);
		return this;
	}
	
	public PublishQueryCondition setExtendCode(String extendCode) {
		put("extendCode",extendCode);
		return this;
	}
	
	public PublishQueryCondition setValid(Boolean valid) {
		put("valid",valid);
		return this;
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
