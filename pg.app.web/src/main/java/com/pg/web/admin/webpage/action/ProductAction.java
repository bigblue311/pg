package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.ProductDO;

public class ProductAction {
	
	@Autowired
	private ProductManager productManager;
	
	public void doUpdate(@FormGroup("product") ProductDO productDO){
		if(productDO.getId() == null){
			productManager.createProduct(productDO);
		} else {
			productManager.updateProduct(productDO);
		}
	}
	
	public void doDelete(@FormGroup("product") ProductDO productDO){
		productManager.deleteProduct(productDO.getId());
	}
}
