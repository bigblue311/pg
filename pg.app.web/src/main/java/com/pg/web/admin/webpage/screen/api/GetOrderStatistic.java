package com.pg.web.admin.webpage.screen.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.biz.manager.TransactionManager;
import com.pg.biz.model.OrderStatisticVO;

public class GetOrderStatistic {
	
	@Autowired
	private TransactionManager transactionManager;
	
	public OrderStatisticVO execute() {
		return transactionManager.getTodayOrderStatistic();
	}
}
