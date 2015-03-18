package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.dal.cache.SystemConfigCache;
import com.pg.dal.model.SystemConfigDO;

public class SystemConfigAction {
	
	@Autowired
	private SystemConfigCache systemConfigCache;
	
	public void doUpdate(@FormGroup("systemConfig") SystemConfigDO systemConfigDO, Navigator nav) {
		systemConfigCache.updateDB(systemConfigDO);
		systemConfigCache.reload();
		nav.redirectTo("admin").withTarget("system.vm");
	}
}
