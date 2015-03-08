package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class ProductQueryCondition extends QueryCondition {
	
	public ProductQueryCondition setName(String name) {
		put("name",name);
		return this;
	}
	
	public ProductQueryCondition setTitle(String title) {
		put("title",title);
		return this;
	}
	
	public ProductQueryCondition setCode(String code) {
		put("code",code);
		return this;
	}
	
	public ProductQueryCondition setBrandId(Long brandId) {
		put("brandId",brandId);
		return this;
	}
	
	public ProductQueryCondition setCategoryId(Long categoryId) {
		put("categoryId",categoryId);
		return this;
	}
	
	@Override
	public ProductQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public ProductQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public ProductQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public ProductQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
