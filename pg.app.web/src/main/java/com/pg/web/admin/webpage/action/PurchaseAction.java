package com.pg.web.admin.webpage.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.CustomerManager;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.enumerate.ProdTypeEnum;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.EmployeeDO;
import com.pg.dal.model.PackageDO;
import com.pg.dal.model.ProductDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.web.admin.common.AuthenticationToken;

public class PurchaseAction {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private CustomerManager customerManager;
	
	public void doUpdate(@FormGroup("purchase") PurchaseDO purchaseDO) throws Exception{
		EmployeeDO loginedUser = AuthenticationToken.get(session);
		getFull(purchaseDO);
		if(purchaseDO.getId() == null){
			transactionManager.createPurchase(purchaseDO);
		} else {
			transactionManager.updatePurchase(purchaseDO,loginedUser.getId());
		}
	}
	
	private void getFull(PurchaseDO from){
		CustomerDO customer = customerManager.getById(from.getCustomerId());
		from.setCustomerName(customer.getName());
		
		if(ProdTypeEnum.商品.getCode().equals(from.getProdType())){
			ProductDO productDO = productManager.getProductById(from.getExtendId());
			from.setExtendCode(productDO.getCode());
			from.setName(productDO.getName());
			from.setTitle(productDO.getTitle());
		}
		if(ProdTypeEnum.商品包.getCode().equals(from.getProdType())){
			PackageDO packageDO = productManager.getPackageById(from.getExtendId());
			from.setExtendCode(packageDO.getCode());
			from.setName(packageDO.getName());
			from.setTitle(packageDO.getTitle());
		}
	}
	
	public void doDelete(@FormGroup("purchase") PurchaseDO purchaseDO){
		productManager.deletePublish(purchaseDO.getId());
	}
}
