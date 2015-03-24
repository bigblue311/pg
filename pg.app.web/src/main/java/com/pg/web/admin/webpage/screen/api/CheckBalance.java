package com.pg.web.admin.webpage.screen.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.model.PublishDO;
import com.pg.dal.model.PurchaseDO;
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
		Long balance = publishDO.getBalance();
		Long limitBuy = publishDO.getLimitBuy();
		if(purchaseId == null){
			if(limitBuy != null && quantity>limitBuy.intValue()){
				return Result.newInstance(false, "超过了最大购买限制", false);
			}
			if(balance != null && quantity>balance.intValue()){
				return Result.newInstance(false, "超过了库存剩余", false);
			}
			return Result.newInstance(true, "库存满足", true);
		} else {
			PurchaseDO purchaseDO = transactionManager.getPurchaseDOById(purchaseId);
			if(limitBuy != null && quantity>limitBuy.intValue()){
				return Result.newInstance(false, "超过了最大购买限制", false);
			}
			if(balance != null && (quantity-purchaseDO.getQuantity())>balance.intValue()){
				return Result.newInstance(false, "超过了库存剩余", false);
			}
			return Result.newInstance(true, "库存满足", true);
		}
	}
}
