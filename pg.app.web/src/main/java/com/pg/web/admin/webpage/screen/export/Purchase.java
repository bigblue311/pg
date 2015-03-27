package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.enumerate.ProdTypeEnum;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.query.PurchaseQueryCondition;

public class Purchase {
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private ProductManager productManager;
	
	public void execute(@Params PurchaseQueryCondition queryCondition,
						Context context) {
		List<PurchaseDO> pageList = transactionManager.getPurchaseDOByCondition(queryCondition);
		
		context.put("list", JSONObject.toJSONString(pageList));
		context.put("prodTypeEnum", ProdTypeEnum.getAll());
		context.put("productEnum", productManager.getProductEnumMap().entrySet());
		context.put("packageEnum", productManager.getPackageEnumMap().entrySet());
	}
}
