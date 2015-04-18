package com.pg.web.admin.webpage.screen.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.model.PublishDO;
import com.victor.framework.common.shared.Result;

public class CheckBalance {
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private TransactionManager transactionManager;
	
	public Result<Boolean> execute(@Param("purchaseId") Long purchaseId,
								   @Param("publishId") Long publishId,
								   @Param("quantity") Integer quantity) {
		PublishDO publishDO = productManager.getPublishById(publishId);
		if(publishDO == null || !publishDO.isValid()){
			return Result.newInstance(false, "该商品已下架", false);
		}
		Integer limitBuy = publishDO.getLimitBuyQuantity();
		if(purchaseId == null){
			if(limitBuy != null && quantity<limitBuy.intValue()){
				return Result.newInstance(false, "低于了最小预定量", false);
			}
			return Result.newInstance(true, "可以购买", true);
		} else {
			if(limitBuy != null && quantity<limitBuy.intValue()){
				return Result.newInstance(false, "低于了最小预定量", false);
			}
			return Result.newInstance(true, "可以购买", true);
		}
	}
}
