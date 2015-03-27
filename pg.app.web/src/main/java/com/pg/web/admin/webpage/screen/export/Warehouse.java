package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.model.WarehouseDO;
import com.pg.dal.query.WarehouseQueryCondition;

public class Warehouse {
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public void execute(@Params WarehouseQueryCondition queryCondition,
						Context context){
		List<WarehouseDO> pageList = warehouseManager.getByCondition(queryCondition);
		context.put("list", JSONObject.toJSONString(pageList));
	}
}
