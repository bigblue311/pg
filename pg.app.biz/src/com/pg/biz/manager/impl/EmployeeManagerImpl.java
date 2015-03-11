package com.pg.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.biz.manager.EmployeeManager;
import com.pg.dal.dao.EmployeeDAO;
import com.pg.dal.model.EmployeeDO;
import com.pg.dal.query.EmployeeQueryCondition;
import com.victor.framework.common.shared.Result;
import com.victor.framework.common.tools.LogTools;
import com.victor.framework.common.tools.MD5;
import com.victor.framework.common.tools.StringTools;

public class EmployeeManagerImpl implements EmployeeManager{

	LogTools logger = new LogTools(EmployeeManagerImpl.class);
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	public void create(EmployeeDO employeeDO) {
		employeeDO.setPassword(MD5.getMD5(employeeDO.getPassword().getBytes()));
		employeeDAO.insert(employeeDO);
	}

	@Override
	public void update(EmployeeDO employeeDO) {
		employeeDO.setPassword(MD5.getMD5(employeeDO.getPassword().getBytes()));
		employeeDAO.update(employeeDO);
	}

	@Override
	public void delete(Long id) {
		employeeDAO.softDelete(id);
	}

	@Override
	public EmployeeDO getById(Long id) {
		return employeeDAO.getById(id);
	}

	@Override
	public List<EmployeeDO> getAll() {
		EmployeeQueryCondition queryCondition = new EmployeeQueryCondition();
		return employeeDAO.getByCondition(queryCondition);
	}

	@Override
	public Result<EmployeeDO> login(String name, String password) {
		if(StringTools.isAnyEmpty(name,password)){
			return Result.newInstance(null, "用户名或密码为空", false);
		}
		EmployeeQueryCondition queryCondition = new EmployeeQueryCondition();
		queryCondition.setName(name).setPassword(MD5.getMD5(password.getBytes()));
		List<EmployeeDO> list = employeeDAO.getByCondition(queryCondition);
		if(list.isEmpty() || list.size() == 0){
			return Result.newInstance(null, "用户名不存在或密码错误", false);
		}
		if(list.size() > 1){
			logger.error("账号异常,发现多个相同的账号:"+name);
			return Result.newInstance(null, "账号异常", false);
		}
		return Result.newInstance(list.get(0), "登录成功", true);
	}

	@Override
	public Boolean checkExist(String name) {
		EmployeeQueryCondition queryCondition = new EmployeeQueryCondition();
		queryCondition.setName(name).clearEnable();
		List<EmployeeDO> list = employeeDAO.getByCondition(queryCondition);
		return !list.isEmpty();
	}

}
