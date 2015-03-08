package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.common.tools.MD5;
import com.victor.framework.common.tools.StringTools;
import com.victor.framework.dal.basic.QueryCondition;

public class CustomerQueryCondition extends QueryCondition {
	
	public CustomerQueryCondition setName(String name) {
		put("name",name);
		return this;
	}
	
	public CustomerQueryCondition setPassword(String password) {
		if(StringTools.isNotEmpty(password)){
			put("password",MD5.getMD5(password.getBytes()));
		}
		return this;
	}
	
	public CustomerQueryCondition setMobile(String mobile) {
		put("mobile",mobile);
		return this;
	}
	
	@Override
	public CustomerQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public CustomerQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public CustomerQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public CustomerQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
