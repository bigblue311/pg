package com.pg.web.admin.webpage.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.EmployeeManager;
import com.pg.dal.model.EmployeeDO;
import com.pg.web.admin.common.AuthenticationToken;
import com.victor.framework.common.shared.Result;

public class LoginAction {
	
	@Autowired
    private HttpSession session;
	
	@Autowired
	private EmployeeManager employeeManager;
	
	public void doLogin(@FormGroup("login") EmployeeDO employeeDO, Navigator nav,Context context) {
		Result<EmployeeDO> result = employeeManager.login(employeeDO.getName(), employeeDO.getPassword());
		if(result.isSuccess()){
			AuthenticationToken.set(session, result.getDataObject());
			nav.redirectTo("admin").withTarget("welcome.vm");
		} else {
			nav.redirectTo("admin").withTarget("login.vm");
		}
	}
	
	public void doLogout(Navigator nav){
		AuthenticationToken.expire(session);
		nav.redirectTo("admin").withTarget("login.vm");
	}
}
