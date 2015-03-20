package com.pg.web.admin.webpage.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.SecurityManager;
import com.pg.dal.model.ResourceRoleDO;
import com.pg.web.admin.model.form.ResourceRolesFO;

public class ResourceRoleAction {
	
	@Autowired
	private SecurityManager securityManager;
	
	public void doUpdate(@FormGroup("resourceRole") ResourceRolesFO resourceRolesFO, 
						 Navigator nav) {
		List<ResourceRoleDO> list = resourceRolesFO.getDO();
		for(ResourceRoleDO resourceRoleDO : list){
			securityManager.create(resourceRoleDO);
		}
		Long roleId = resourceRolesFO.getRoleId();
		if(roleId == null){
			nav.redirectTo("admin").withTarget("resource.vm");
		} else {
			nav.redirectTo("admin").withTarget("resource.vm").withParameter("roleId", roleId.toString());
		}
	}
	
	public void doDelete(@FormGroup("resourceRole") ResourceRolesFO resourceRolesFO, 
						 Navigator nav) {
		List<ResourceRoleDO> list = resourceRolesFO.getDO();
		for(ResourceRoleDO resourceRoleDO : list){
			securityManager.delete(resourceRoleDO);
		}
		Long roleId = resourceRolesFO.getRoleId();
		if(roleId == null){
			nav.redirectTo("admin").withTarget("resource.vm");
		} else {
			nav.redirectTo("admin").withTarget("resource.vm").withParameter("roleId", roleId.toString());
		}
	}
}
