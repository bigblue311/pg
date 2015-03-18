package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import com.pg.biz.manager.EmployeeManager;
import com.pg.dal.enumerate.RoleEnum;
import com.pg.dal.model.EmployeeDO;

public class Employee {
	
	@Autowired
	private EmployeeManager employeeManager;
	
	public void execute(Context context) {
		List<EmployeeDO> list = employeeManager.getAll();
		context.put("list", JSONObject.toJSONString(list));
		context.put("roleEnum", RoleEnum.getAll());
	}
}
