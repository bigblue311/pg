package com.pg.web.admin.webpage.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.enumerate.ProdTypeEnum;
import com.pg.dal.model.PackageDO;
import com.pg.dal.model.ProductDO;
import com.pg.dal.model.PublishDO;
import com.pg.web.admin.model.form.PublishFO;

public class PublishAction {
	
	@Autowired
	private ProductManager productManager;
	
	public void doUpdate(@FormGroup("publish") PublishFO publishFO){
		PublishDO publishDO = publishFO.getDO();
		getFull(publishDO);
		if(publishDO.getId() == null){
			productManager.createPublish(publishDO);
		} else {
			productManager.updatePublish(publishDO);
		}
	}
	
	private void getFull(PublishDO form){
		if(ProdTypeEnum.产品.getCode().equals(form.getProdType())){
			ProductDO productDO = productManager.getProductById(form.getExtendId());
			form.setExtendCode(productDO.getCode());
			form.setName(productDO.getName());
			form.setTitle(productDO.getTitle());
		}
		if(ProdTypeEnum.产品包.getCode().equals(form.getProdType())){
			PackageDO packageDO = productManager.getPackageById(form.getExtendId());
			form.setExtendCode(packageDO.getCode());
			form.setName(packageDO.getName());
			form.setTitle(packageDO.getTitle());
		}
	}
	
	public void doDelete(@FormGroup("publish") PublishFO publishFO){
		productManager.deletePublish(publishFO.getId());
	}
}
