package com.victor.framework.search.basic;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;
import com.victor.framework.search.enumerate.SearchResultEnum;

public class SearchQuery extends QueryCondition {
	public SearchQuery(SearchResultEnum searchResultEnum){
		this.searchResultEnum = searchResultEnum;
		put("start", 0);
		put("pageSize", 5000);
	}
	
	private SearchResultEnum searchResultEnum;
	
	public SearchResultEnum getSearchResultEnum() {
		return searchResultEnum;
	}
	
	public SearchQuery setShopId(Long shopId){
		put("shopId", shopId);
		return this;
	}
	
	public SearchQuery setCustomerId(Long customerId){
		put("customerId", customerId);
		return this;
	}
	public SearchQuery setKeyword(String keyword) {
		put("keyword",keyword.replace("'", ""));
		return this;
	}
	public SearchQuery setTagId(Long tagId) {
		put("tagId",tagId);
		return this;
	}
	public SearchQuery setCategoryId(Long categoryId) {
		put("categoryId",categoryId);
		return this;
	}
	@Override
	public SearchQuery setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public SearchQuery setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public SearchQuery setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public SearchQuery setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[searchResultEnum:");
		sb.append(this.getSearchResultEnum().getCode());
		for(String key : this.getQueryMap().keySet()){
			sb.append(",");
			sb.append(key);
			sb.append(":");
			Object value = this.getQueryMap().get(key);
			if(value instanceof Date){
				sb.append(DateTools.DateToString((Date)value));
			} else if(value == null){ 
				sb.append("");
			}else {
				sb.append(value.toString());
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
