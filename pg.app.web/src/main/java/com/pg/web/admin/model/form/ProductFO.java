package com.pg.web.admin.model.form;

import java.util.Date;

import com.pg.dal.model.ProductDO;
import com.victor.framework.common.tools.DateTools;

public class ProductFO extends ProductDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1005217326698159014L;
	private String gmtValidFrom;
	private String gmtExpTo;
	
	public String getGmtValidFrom() {
		return gmtValidFrom;
	}

	public void setGmtValidFrom(String gmtValidFrom) {
		this.gmtValidFrom = gmtValidFrom;
		this.setValidFrom(DateTools.getDayBegin(StringToDate(gmtValidFrom)));
	}

	public String getGmtExpTo() {
		return gmtExpTo;
	}

	public void setGmtExpTo(String gmtExpTo) {
		this.gmtExpTo = gmtExpTo;
		this.setExpTo(DateTools.getDayBegin(StringToDate(gmtExpTo)));
	}

	protected Date StringToDate(String date){
		Date toDate = DateTools.today();
		try {
			toDate = DateTools.StringToDate(date);
		} catch (Exception e) {
			toDate = DateTools.today();
		}
		return toDate==null?DateTools.today():toDate;
	}
	
	public ProductDO getDO(){
		ProductDO productDO = new ProductDO();
		productDO.setId(getId());
		productDO.setName(getName());
		productDO.setTitle(getTitle());
		productDO.setBrandId(getBrandId());
		productDO.setCategoryId(getCategoryId());
		productDO.setCode(getCode());
		productDO.setBarcode(getBarcode());
		productDO.setBoxcode(getBoxcode());
		productDO.setSpec(getSpec());
		productDO.setPackageSpec(getPackageSpec());
		productDO.setMsu(getMsu());
		productDO.setPrice3500(getPrice3500());
		productDO.setPrice2000(getPrice2000());
		productDO.setPrice800(getPrice800());
		productDO.setPrice200(getPrice200());
		productDO.setPrice100(getPrice100());
		productDO.setPriceSugg(getPriceSugg());
		productDO.setCubage(getCubage());
		productDO.setWeight(getWeight());
		productDO.setValidFrom(getValidFrom());
		productDO.setExpTo(getExpTo());
		productDO.setEnable(getEnable());
		productDO.setDescription(getDescription());
		return productDO;
	}
}
