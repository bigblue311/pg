package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.BrandManager;
import com.pg.biz.manager.CategoryManager;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.ProductDO;
import com.pg.dal.query.ProductQueryCondition;

public class Product {
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private BrandManager brandManager;
	
	@Autowired
	private CategoryManager categoryManager;
	
	public void execute(@Params ProductQueryCondition queryCondition,
						Context context){
		List<ProductDO> list = productManager.getProductByCondition(queryCondition);
		
		context.put("list", JSONObject.toJSONString(list));
		context.put("brandEnum", brandManager.getEnumMap().entrySet());
		context.put("categoryEnum", categoryManager.getEnumMap().entrySet());
	}
}
