package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.enumerate.OrderStatusEnum;
import com.pg.dal.model.OrderDO;
import com.pg.dal.query.OrderQueryCondition;

public class Order {
	
	@Autowired
	private TransactionManager transactionManager;
	
	public void execute(@Params OrderQueryCondition queryCondition,
						Context context) {
		List<OrderDO> pageList = transactionManager.getOrderDOList(queryCondition);
		
		context.put("list", JSONObject.toJSONString(pageList));
		context.put("statusEnum", OrderStatusEnum.getAll());
	}
}
