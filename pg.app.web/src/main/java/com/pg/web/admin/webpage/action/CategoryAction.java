package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.CategoryManager;
import com.pg.dal.model.CategoryDO;

public class CategoryAction {
	
	@Autowired
	private CategoryManager CategoryManager;
	
	public void doUpdate(@FormGroup("category") CategoryDO categoryDO, Navigator nav) {
		if(categoryDO.getId() == null ) {
			CategoryManager.create(categoryDO);
		} else {
			CategoryManager.update(categoryDO);
		}
		Long parentId = categoryDO.getParentId();
		if(parentId == null || parentId == 0l){
			nav.redirectTo("admin").withTarget("category.vm");
		} else {
			nav.redirectTo("admin").withTarget("category.vm").withParameter("parentId", parentId.toString());
		}
	}
	
	public void doDelete(@FormGroup("category") CategoryDO categoryDO, Navigator nav) {
		CategoryManager.delete(categoryDO.getId());
		Long parentId = categoryDO.getParentId();
		if(parentId == null || parentId == 0l){
			nav.redirectTo("admin").withTarget("category.vm");
		} else {
			nav.redirectTo("admin").withTarget("category.vm").withParameter("parentId", parentId.toString());
		}
	}
}
