package com.pg.dal.model;

import java.io.Serializable;
import java.util.Date;

import com.pg.dal.enumerate.EnableEnum;
import com.pg.dal.query.ProductQueryCondition;
import com.victor.framework.annotation.EnumValue;
import com.victor.framework.common.tools.StringTools;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 产品
 * @author victorhan
 *
 */
public class ProductDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 923264648570038956L;
	
	@EnumValue
	private String name;		//名称
	private String title;		//标题
	private Long brandId;		//品牌ID
	private Long categoryId;	//品类ID
	private String code;		//编号
	private String barcode;		//条形码
	private String boxcode;		//箱码
	private String spec;		//规格
	private String packageSpec;	//包装系数
	private String msu;			//MSU
	private Double price3500;	//3500箱价格
	private Double price2000;	//2000箱价格
	private Double price800;	//800箱价格
	private Double price200;	//200箱价格
	private Double price100;	//100箱价格
	private Double priceSugg;	//建议售价
	private String cubage;		//体积
	private Double weight;		//公斤/箱
	private Date   validFrom;	//信息生效日期
	private Date   expTo;		//保质期
	private String enable;		//有效
	private String description;	//描述
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getBoxcode() {
		return boxcode;
	}
	public void setBoxcode(String boxcode) {
		this.boxcode = boxcode;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPackageSpec() {
		return packageSpec;
	}
	public void setPackageSpec(String packageSpec) {
		this.packageSpec = packageSpec;
	}
	public String getMsu() {
		return msu;
	}
	public void setMsu(String msu) {
		this.msu = msu;
	}
	public Double getPrice3500() {
		return price3500;
	}
	public void setPrice3500(Double price3500) {
		this.price3500 = price3500;
	}
	public Double getPrice2000() {
		return price2000;
	}
	public void setPrice2000(Double price2000) {
		this.price2000 = price2000;
	}
	public Double getPrice800() {
		return price800;
	}
	public void setPrice800(Double price800) {
		this.price800 = price800;
	}
	public Double getPrice200() {
		return price200;
	}
	public void setPrice200(Double price200) {
		this.price200 = price200;
	}
	public Double getPrice100() {
		return price100;
	}
	public void setPrice100(Double price100) {
		this.price100 = price100;
	}
	public Double getPriceSugg() {
		return priceSugg;
	}
	public void setPriceSugg(Double priceSugg) {
		this.priceSugg = priceSugg;
	}
	public String getCubage() {
		return cubage;
	}
	public void setCubage(String cubage) {
		this.cubage = cubage;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getExpTo() {
		return expTo;
	}
	public void setExpTo(Date expTo) {
		this.expTo = expTo;
	}
	public boolean isValid(){
		if(StringTools.isEmpty(enable)){
			return false;
		}
		if(EnableEnum.无效.getCode().equals(enable)){
			return false;
		}
		return true;
	}
	
	public ProductQueryCondition toQueryCondition(){
		ProductQueryCondition queryCondition = new ProductQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
