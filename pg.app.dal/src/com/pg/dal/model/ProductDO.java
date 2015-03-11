package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.enumerate.EnableEnum;
import com.pg.dal.query.ProductQueryCondition;
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
	private String name;		//名称
	private String title;		//标题
	private String code;		//编号
	private String img;			//图片
	private Long brandId;		//品牌ID
	private Long categoryId;	//品类ID
	private String description;	//描述
	private String enable;		//有效
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
