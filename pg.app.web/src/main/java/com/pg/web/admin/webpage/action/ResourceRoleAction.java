package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.SecurityManager;
import com.pg.dal.model.ResourceRoleDO;

public class ResourceRoleAction {
	
	@Autowired
	private SecurityManager securityManager;
	
	public void doUpdate(@FormGroup("resourceRole") ResourceRoleDO resourceRoleDO, Navigator nav) {
		securityManager.create(resourceRoleDO);
		Long roleId = resourceRoleDO.getRoleId();
		if(roleId == null){
			nav.redirectTo("admin").withTarget("resource.vm");
		} else {
			nav.redirectTo("admin").withTarget("resource.vm").withParameter("roleId", roleId.toString());
		}
	}
	
	public void doDelete(@FormGroup("resourceRole") ResourceRoleDO resourceRoleDO, Navigator nav) {
		securityManager.delete(resourceRoleDO);
		Long roleId = resourceRoleDO.getRoleId();
		if(roleId == null){
			nav.redirectTo("admin").withTarget("resource.vm");
		} else {
			nav.redirectTo("admin").withTarget("resource.vm").withParameter("roleId", roleId.toString());
		}
	}
}
