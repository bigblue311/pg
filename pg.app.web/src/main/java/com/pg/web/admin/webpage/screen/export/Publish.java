package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.enumerate.ProdTypeEnum;
import com.pg.dal.enumerate.EnableEnum;
import com.pg.dal.model.PublishDO;
import com.pg.dal.query.PublishQueryCondition;

public class Publish {
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public void execute(@Params PublishQueryCondition queryCondition,
						Context context){
		List<PublishDO>	pageList = productManager.getPublishByCondition(queryCondition);
		
		context.put("list", JSONObject.toJSONString(pageList));
		context.put("prodTypeEnum", ProdTypeEnum.getAll());
		context.put("enableEnum", EnableEnum.getAll());
		context.put("productEnum", productManager.getProductEnumMap().entrySet());
		context.put("packageEnum", productManager.getPackageEnumMap().entrySet());
		context.put("warehouseEnum", warehouseManager.getEnumMap(0l).entrySet());
	}
}
