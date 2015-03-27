package com.pg.web.admin.webpage.screen.export;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.CustomerManager;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.query.CustomerQueryCondition;

public class Customer {
	
	@Autowired
	private CustomerManager customerManager;
	
	public void execute(@Params CustomerQueryCondition queryCondition,
						Context context){
		List<CustomerDO> pageList = customerManager.getByCondition(queryCondition);
		
		context.put("list", JSONObject.toJSONString(pageList));
	}
}
