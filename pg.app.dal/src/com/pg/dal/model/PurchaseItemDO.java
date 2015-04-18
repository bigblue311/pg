package com.pg.dal.model;

import java.io.Serializable;
import java.util.Date;

import com.pg.dal.query.PurchaseItemQueryCondition;
import com.victor.framework.annotation.EnumValue;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 订单商品
 * @author victorhan
 *
 */
public class PurchaseItemDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 923264648570038956L;
	
	@EnumValue
	private String 	name;			//名称
	private String 	title;			//标题
	private Long 	brandId;		//品牌ID
	private Long 	categoryId;		//品类ID
	private String 	code;			//编号
	private String 	barcode;		//条形码
	private String 	boxcode;		//箱码
	private String 	spec;			//规格
	private String 	packageSpec;	//包装系数
	private String 	msu;			//MSU
	private Double 	price;			//成交价格
	private Integer quantity;		//数量
	private String 	cubage;			//体积
	private Double 	weight;			//公斤/箱
	private Date   	validFrom;		//信息生效日期
	private Date   	expTo;			//保质期
	private String 	description;	//描述
	private Long 	purchaseId;		//订单购买ID
	private Long	productId;		//产品ID
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public PurchaseItemQueryCondition toQueryCondition(){
		PurchaseItemQueryCondition queryCondition = new PurchaseItemQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
