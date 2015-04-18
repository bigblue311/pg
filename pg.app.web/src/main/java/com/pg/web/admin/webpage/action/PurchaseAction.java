package com.pg.web.admin.webpage.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.CustomerManager;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.model.EmployeeDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.web.admin.common.AuthenticationToken;

public class PurchaseAction {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private CustomerManager customerManager;
	
	public void doUpdate(@FormGroup("purchase") PurchaseDO purchaseDO, 
						 @FormField(name="customerId",group="FormField") Long customerId) throws Exception{
		EmployeeDO loginedUser = AuthenticationToken.get(session);
		if(purchaseDO.getId() == null){
			transactionManager.createPurchase(purchaseDO,loginedUser.getId(),customerId);
		} else {
			transactionManager.updatePurchase(purchaseDO,loginedUser.getId(),customerId);
		}
	}
	
	public void doDelete(@FormGroup("purchase") PurchaseDO purchaseDO,
						 @FormField(name="customerId",group="FormField") Long customerId){
		EmployeeDO loginedUser = AuthenticationToken.get(session);
		transactionManager.deletePurchase(purchaseDO.getId(),loginedUser.getId(),customerId);
	}
}
