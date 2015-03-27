package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.PackageDO;
import com.pg.dal.query.PackageQueryCondition;

public class Package {
	
	@Autowired
	private ProductManager productManager;
	
	public void execute(@Params PackageQueryCondition queryCondition,
						Context context){
		List<PackageDO> list = productManager.getPackageByCondition(queryCondition);
		
		context.put("list", JSONObject.toJSONString(list));
	}
}
