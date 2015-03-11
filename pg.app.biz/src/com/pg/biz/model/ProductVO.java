package com.pg.biz.model;

import java.io.Serializable;

import com.pg.dal.model.ProductDO;
import com.pg.dal.model.PublishDO;
import com.pg.dal.model.WarehouseDO;
import com.victor.framework.common.tools.StringTools;

public class ProductVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2910530415442674030L;
	
	private ProductDO productDO;
	private PublishDO publishDO;
	private WarehouseDO warehouseDO;
	
	private String img;
	private String description;
	private Boolean valid;
	
	public ProductDO getProductDO() {
		return productDO;
	}
	public void setProductDO(ProductDO productDO) {
		this.productDO = productDO;
	}
	public PublishDO getPublishDO() {
		return publishDO;
	}
	public void setPublishDO(PublishDO publishDO) {
		this.publishDO = publishDO;
	}
	public WarehouseDO getWarehouseDO() {
		return warehouseDO;
	}
	public void setWarehouseDO(WarehouseDO warehouseDO) {
		this.warehouseDO = warehouseDO;
	}
	public String getImg() {
		if(StringTools.isNotEmpty(img)){
			return img;
		}
		if(publishDO!=null && StringTools.isNotEmpty(publishDO.getImg())){
			return publishDO.getImg();
		}
		if(productDO!=null && StringTools.isNotEmpty(productDO.getImg())){
			return productDO.getImg();
		}
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDescription() {
		if(StringTools.isNotEmpty(description)){
			return description;
		}
		if(publishDO!=null && StringTools.isNotEmpty(publishDO.getDescription())){
			return publishDO.getDescription();
		}
		if(productDO!=null && StringTools.isNotEmpty(productDO.getDescription())){
			return productDO.getDescription();
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getValid() {
		if(valid != null){
			return valid;
		}
		if(publishDO!=null && productDO!=null){
			return publishDO.isValid() && productDO.isValid();
		}
		return false;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
}
