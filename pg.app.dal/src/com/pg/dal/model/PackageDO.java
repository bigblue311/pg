package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.PackageQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 产品包
 * @author victorhan
 *
 */
public class PackageDO extends EntityDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4950264146722336203L;
	private String name;	//名称
	private String title;	//标题
	private String code;	//编号
	private String img;		//图片
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
	
	public PackageQueryCondition toQueryCondition(){
		PackageQueryCondition queryCondition = new PackageQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
