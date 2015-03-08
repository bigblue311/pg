package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class WarehouseQueryCondition extends QueryCondition {
	
	public WarehouseQueryCondition setName(String name) {
		put("name",name);
		return this;
	}
	
	public WarehouseQueryCondition setCustomerId(Long customerId) {
		put("customerId",customerId);
		return this;
	}
	
	public WarehouseQueryCondition setSystem(Boolean system) {
		put("system",system);
		return this;
	}
	
	@Override
	public WarehouseQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public WarehouseQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public WarehouseQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public WarehouseQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
