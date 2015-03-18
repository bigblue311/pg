package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.EmployeeManager;
import com.pg.dal.enumerate.RoleEnum;
import com.pg.dal.model.EmployeeDO;
import com.pg.web.admin.common.CrumbLink;
import com.pg.web.admin.enumerate.ResourceEnum;

public class Employee {
	
	@Autowired
	private EmployeeManager employeeManager;
	
	public void execute(Context context) {
		setCrumb(context);
		List<EmployeeDO> list = employeeManager.getAll();
		context.put("list", JSONObject.toJSONString(list));
		context.put("roleEnum", RoleEnum.getAll());
	}
	
	private void setCrumb(Context context){
		List<CrumbLink> crumbs = Lists.newLinkedList();
		crumbs.add(new CrumbLink(ResourceEnum.后台账号.getDesc(),ResourceEnum.后台账号.getUri()));
		context.put("crumbs", crumbs);
	}
}
