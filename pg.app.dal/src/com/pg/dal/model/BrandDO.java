package com.pg.dal.model;

import java.io.Serializable;

import com.pg.dal.query.BrandQueryCondition;
import com.victor.framework.dal.basic.EntityDO;

/**
 * 品牌
 * @author victorhan
 *
 */
public class BrandDO extends EntityDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180648161565465644L;
	
	private String name;		//名称
	private Long parentId;		//父节点ID
	private String enable;		//是否有效
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public BrandQueryCondition toQueryCondition(){
		BrandQueryCondition queryCondition = new BrandQueryCondition();
		queryCondition.setQueryMap(this.toMap());
		return queryCondition;
	}
}
