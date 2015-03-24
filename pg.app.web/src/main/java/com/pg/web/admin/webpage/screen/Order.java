package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.CustomerManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.enumerate.OrderStatusEnum;
import com.pg.dal.enumerate.ResourceEnum;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.query.OrderQueryCondition;
import com.pg.dal.query.WarehouseQueryCondition;
import com.pg.web.admin.model.json.CrumbJson;
import com.victor.framework.dal.basic.Paging;

public class Order {
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private CustomerManager customerManager;
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public void execute(@Params OrderQueryCondition queryCondition,
						Context context) {
		Long customerId = queryCondition.getCustomerId();
		setCrumb(context,customerId);
		loadWarehouseInfo(context,customerId);
		Paging<OrderDO> pageList = Paging.emptyPage();
		pageList = transactionManager.getOrderDOPage(queryCondition);
		
		context.put("query", queryCondition);
		context.put("paging", pageList);
		context.put("list", JSONObject.toJSONString(pageList.getData()));
		context.put("statusEnum", OrderStatusEnum.getAll());
	}
	
	private void loadWarehouseInfo(Context context,Long customerId){
		WarehouseQueryCondition queryCondition = new WarehouseQueryCondition();
		queryCondition.setSystem(true);
		context.put("mywarehouse", JSONObject.toJSONString(warehouseManager.getByCondition(queryCondition)));
		
		if(customerId != null) {
			queryCondition = new WarehouseQueryCondition();
			queryCondition.setCustomerId(customerId);
			context.put("warehouse", JSONObject.toJSONString(warehouseManager.getByCondition(queryCondition)));
		} else {
			context.put("warehouse", Lists.newArrayList());
		}
	}
	
	private void setCrumb(Context context,Long customerId){
		List<CrumbJson> crumbs = Lists.newLinkedList();
		crumbs.add(new CrumbJson(ResourceEnum.订单管理.getName(),ResourceEnum.订单管理.getUri()));
		if(customerId != null) {
			CustomerDO customerDO = customerManager.getById(customerId);
			crumbs.add(new CrumbJson(customerDO.getName()+"的订单",ResourceEnum.订单管理.getUri()+"?customerId="+customerId));
		
			context.put("customerName", customerDO.getName());
		}
		context.put("crumbs", crumbs);
		context.put("crumbDesc", ResourceEnum.订单管理.getDesc());
	}
}
