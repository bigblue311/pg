package com.pg.dal.query;

import java.util.Date;

import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.QueryCondition;

public class OrderQueryCondition extends QueryCondition {
	
	public OrderQueryCondition setAddressFrom(String addressFrom) {
		put("addressFrom",addressFrom);
		return this;
	}
	
	public String getAddressFrom(){
		return getString("addressFrom");
	}
	
	public OrderQueryCondition setAddressTo(String addressTo) {
		put("addressTo",addressTo);
		return this;
	}
	
	public String getAddressTo(){
		return getString("addressTo");
	}
	
	public OrderQueryCondition setKeeper(String keeper) {
		put("keeper",keeper);
		return this;
	}
	
	public String getKeeper(){
		return getString("keeper");
	}
	
	public OrderQueryCondition setPhone(String phone) {
		put("phone",phone);
		return this;
	}
	
	public String getPhone(){
		return getString("phone");
	}
	
	public OrderQueryCondition setMobile(String mobile) {
		put("mobile",mobile);
		return this;
	}
	
	public String getMobile(){
		return getString("mobile");
	}
	
	public OrderQueryCondition setCustomerId(Long customerId) {
		put("customerId",customerId);
		return this;
	}
	
	public Long getCutomerId(){
		return getLong("customerId");
	}
	
	public OrderQueryCondition setDepositStart(Double depositStart) {
		put("depositStart",depositStart);
		return this;
	}
	
	public Double getDepositStart(){
		return getDouble("depositStart");
	}
	
	public OrderQueryCondition setDepositEnd(Double depositEnd) {
		put("depositEnd",depositEnd);
		return this;
	}
	
	public Double getDepositEnd(){
		return getDouble("depositEnd");
	}
	
	public OrderQueryCondition setProdType(String prodType) {
		put("prodType",prodType);
		return this;
	}
	
	public String getProdType(){
		return getString("prodType");
	}
	
	public OrderQueryCondition setExtendId(Long extendId) {
		put("extendId",extendId);
		return this;
	}
	
	public Long getExtendId(){
		return getLong("extendId");
	}
	
	public OrderQueryCondition setExtendCode(Long extendCode) {
		put("extendCode",extendCode);
		return this;
	}
	
	public Long getExtendCode(){
		return getLong("extendCode");
	}
	
	public OrderQueryCondition setQuantityStart(Integer quantityStart) {
		put("quantityStart",quantityStart);
		return this;
	}
	
	public int getQuantityStart(){
		return getInteger("quantityStart");
	}
	
	public OrderQueryCondition setQuantityEnd(Integer quantityEnd) {
		put("quantityEnd",quantityEnd);
		return this;
	}
	
	public int getQuantityEnd(){
		return getInteger("quantityEnd");
	}
	
	public OrderQueryCondition setPriceStart(Double priceStart) {
		put("priceStart",priceStart);
		return this;
	}
	
	public Double getPriceStart(){
		return getDouble("priceStart");
	}
	
	public OrderQueryCondition setPriceEnd(Double priceEnd) {
		put("priceEnd",priceEnd);
		return this;
	}
	
	public Double getPriceEnd(){
		return getDouble("priceEnd");
	}
	
	public OrderQueryCondition setTotalPriceStart(Double totalPriceStart) {
		put("totalPriceStart",totalPriceStart);
		return this;
	}
	
	public Double getTotalPriceStart(){
		return getDouble("totalPriceStart");
	}
	
	public OrderQueryCondition setTotalPriceEnd(Double totalPriceEnd) {
		put("totalPriceEnd",totalPriceEnd);
		return this;
	}
	
	public Double getTotalPriceEnd(){
		return getDouble("totalPriceEnd");
	}
	
	public OrderQueryCondition setTransportFeeStart(Double transportFeeStart) {
		put("transportFeeStart",transportFeeStart);
		return this;
	}
	
	public Double setTransportFeeStart(){
		return getDouble("transportFeeStart");
	}
	
	public OrderQueryCondition setTransportFeeEnd(Double transportFeeEnd) {
		put("transportFeeEnd",transportFeeEnd);
		return this;
	}
	
	public Double getTransportFeeEnd(){
		return getDouble("transportFeeEnd");
	}
	
	public OrderQueryCondition setTransportCode(Double transportCode) {
		put("transportCode",transportCode);
		return this;
	}
	
	public String getTransportCode(){
		return getString("transportCode");
	}
	
	public OrderQueryCondition setStatus(String status) {
		put("status",status);
		return this;
	}
	
	public String getStatus(){
		return getString("status");
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
