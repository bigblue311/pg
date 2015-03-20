package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.ProductDO;

public class ProductAction {
	
	@Autowired
	private ProductManager productManager;
	
	public void doUpdate(@FormGroup("product") ProductDO productDO, Navigator nav){
		if(productDO.getId() == null){
			productManager.createProduct(productDO);
		} else {
			productManager.updateProduct(productDO);
		}
		nav.redirectTo("admin").withTarget("product.vm");
	}
	
	public void doDelete(@FormGroup("product") ProductDO productDO, Navigator nav){
		productManager.deleteProduct(productDO.getId());
		nav.redirectTo("admin").withTarget("product.vm");
	}
}
