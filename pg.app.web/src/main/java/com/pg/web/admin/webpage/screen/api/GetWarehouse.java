package com.pg.web.admin.webpage.screen.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.google.common.collect.Lists;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.model.WarehouseDO;
import com.pg.dal.query.WarehouseQueryCondition;
import com.victor.framework.common.shared.Result;

public class GetWarehouse {
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public Result<List<WarehouseDO>> execute(@Param("customerId") Long customerId,
								   			 @Param("system") Boolean system) {
		List<WarehouseDO> list = Lists.newArrayList();
		WarehouseQueryCondition queryCondition = new WarehouseQueryCondition();
		if(system!=null && system){
			queryCondition.setSystem(system);
		} else {
			if(customerId != null) {
				queryCondition.setCustomerId(customerId);
			} else {
				return Result.newInstance(list, "customerId is null", false);
			}
		}
		list = warehouseManager.getByCondition(queryCondition);
		return Result.newInstance(list, "获取数据成功", !list.isEmpty());
	}
}
