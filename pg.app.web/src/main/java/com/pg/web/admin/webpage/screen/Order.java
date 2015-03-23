package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.enumerate.OrderStatusEnum;
import com.pg.dal.enumerate.ResourceEnum;
import com.pg.dal.model.OrderDO;
import com.pg.dal.query.OrderQueryCondition;
import com.pg.web.admin.model.json.CrumbJson;
import com.victor.framework.dal.basic.Paging;

public class Order {
	
	@Autowired
	private TransactionManager transactionManager;
	
	public void execute(@Params OrderQueryCondition query,
						Context context) {
		setCrumb(context);
		Paging<OrderDO> pageList = Paging.emptyPage();
		pageList = transactionManager.getOrderDOPage(query);
		
		context.put("query", query);
		context.put("paging", pageList);
		context.put("list", JSONObject.toJSONString(pageList.getData()));
		context.put("statusEnum", OrderStatusEnum.getAll());
	}
	
	private void setCrumb(Context context){
		List<CrumbJson> crumbs = Lists.newLinkedList();
		crumbs.add(new CrumbJson(ResourceEnum.订单管理.getName(),ResourceEnum.订单管理.getUri()));
		context.put("crumbs", crumbs);
		context.put("crumbDesc", ResourceEnum.订单管理.getDesc());
	}
}
