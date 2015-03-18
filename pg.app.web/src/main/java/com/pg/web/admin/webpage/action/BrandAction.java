package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.BrandManager;
import com.pg.dal.model.BrandDO;

public class BrandAction {
	
	@Autowired
	private BrandManager brandManager;
	
	public void doUpdate(@FormGroup("brand") BrandDO brandDO, Navigator nav) {
		if(brandDO.getId() == null ) {
			brandManager.create(brandDO);
		} else {
			brandManager.update(brandDO);
		}
		Long parentId = brandDO.getParentId();
		if(parentId == null || parentId == 0l){
			nav.redirectTo("admin").withTarget("brand.vm");
		} else {
			nav.redirectTo("admin").withTarget("brand.vm").withParameter("parentId", parentId.toString());
		}
	}
	
	public void doDelete(@FormGroup("brand") BrandDO brandDO, Navigator nav) {
		brandManager.delete(brandDO.getId());
		Long parentId = brandDO.getParentId();
		if(parentId == null || parentId == 0l){
			nav.redirectTo("admin").withTarget("brand.vm");
		} else {
			nav.redirectTo("admin").withTarget("brand.vm").withParameter("parentId", parentId.toString());
		}
	}
}
