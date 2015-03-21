package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.model.WarehouseDO;

public class WarehouseAction {
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public void doUpdate(@FormGroup("warehouse") WarehouseDO warehouseDO, Navigator nav) {
		if(warehouseDO.getId() == null ) {
			warehouseManager.create(warehouseDO);
		} else {
			warehouseManager.update(warehouseDO);
		}
		if(warehouseDO.getCustomerId() == 0l){
			nav.redirectTo("admin").withTarget("mywarehouse.vm");
		} else {
			nav.redirectTo("admin").withTarget("warehouse.vm");
		}
	}
	
	public void doDelete(@FormGroup("warehouse") WarehouseDO warehouseDO, Navigator nav) {
		warehouseManager.delete(warehouseDO.getId());
		if(warehouseDO.getCustomerId() == 0l){
			nav.redirectTo("admin").withTarget("mywarehouse.vm");
		} else {
			nav.redirectTo("admin").withTarget("warehouse.vm");
		}
	}
}
