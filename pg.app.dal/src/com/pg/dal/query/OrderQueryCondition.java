package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class OrderQueryCondition extends QueryCondition {
	
	public OrderQueryCondition setAddressFrom(String addressFrom) {
		put("addressFrom",addressFrom);
		return this;
	}
	
	public OrderQueryCondition setAddressTo(String addressTo) {
		put("addressTo",addressTo);
		return this;
	}
	
	public OrderQueryCondition setKeeper(String keeper) {
		put("keeper",keeper);
		return this;
	}
	
	public OrderQueryCondition setPhone(String phone) {
		put("phone",phone);
		return this;
	}
	
	public OrderQueryCondition setMobile(String mobile) {
		put("mobile",mobile);
		return this;
	}
	
	public OrderQueryCondition setCustomerId(Long customerId) {
		put("customerId",customerId);
		return this;
	}
	
	public OrderQueryCondition setDepositStart(Double depositStart) {
		put("depositStart",depositStart);
		return this;
	}
	
	public OrderQueryCondition setDepositEnd(Double depositEnd) {
		put("depositEnd",depositEnd);
		return this;
	}
	
	public OrderQueryCondition setProdType(String prodType) {
		put("prodType",prodType);
		return this;
	}
	
	public OrderQueryCondition setExtendId(Long extendId) {
		put("extendId",extendId);
		return this;
	}
	
	public OrderQueryCondition setExtendCode(Long extendCode) {
		put("extendCode",extendCode);
		return this;
	}
	
	public OrderQueryCondition setQuantityStart(Integer quantityStart) {
		put("quantityStart",quantityStart);
		return this;
	}
	
	public OrderQueryCondition setQuantityEnd(Integer quantityEnd) {
		put("quantityEnd",quantityEnd);
		return this;
	}
	
	public OrderQueryCondition setPriceStart(Double priceStart) {
		put("priceStart",priceStart);
		return this;
	}
	
	public OrderQueryCondition setPriceEnd(Double priceEnd) {
		put("priceEnd",priceEnd);
		return this;
	}
	
	public OrderQueryCondition setTotalPriceStart(Double totalPriceStart) {
		put("totalPriceStart",totalPriceStart);
		return this;
	}
	
	public OrderQueryCondition setTotalPriceEnd(Double totalPriceEnd) {
		put("totalPriceEnd",totalPriceEnd);
		return this;
	}
	
	public OrderQueryCondition setTransportFeeStart(Double transportFeeStart) {
		put("transportFeeStart",transportFeeStart);
		return this;
	}
	
	public OrderQueryCondition setTransportFeeEnd(Double transportFeeEnd) {
		put("transportFeeEnd",transportFeeEnd);
		return this;
	}
	
	public OrderQueryCondition setTransportCode(Double transportCode) {
		put("transportCode",transportCode);
		return this;
	}
	
	public OrderQueryCondition setStatus(String status) {
		put("status",status);
		return this;
	}
	
	@Override
	public OrderQueryCondition setGmtModifyStart(Date from){
		put("gmtModifyStart", DateTools.getDayBegin(from));
		return this;
	}
	
	@Override
	public OrderQueryCondition setGmtModifyEnd(Date to){
		put("gmtModifyEnd", DateTools.getDayEnd(to));
		return this;
	}
	
	@Override
	public OrderQueryCondition setStart(int start){
		put("start", start);
		return this;
	}
	
	@Override
	public OrderQueryCondition setPageSize(int pageSize){
		put("pageSize", pageSize);
		return this;
	}
}
