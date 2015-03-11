package com.pg.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.biz.manager.CustomerManager;
import com.pg.dal.dao.CustomerDAO;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.query.CustomerQueryCondition;
import com.victor.framework.common.shared.Result;
import com.victor.framework.common.tools.LogTools;
import com.victor.framework.common.tools.MD5;
import com.victor.framework.common.tools.StringTools;

public class CustomerManagerImpl implements CustomerManager{

	LogTools logger = new LogTools(CustomerManagerImpl.class);
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public void create(CustomerDO customerDO) {
		customerDO.setPassword(MD5.getMD5(customerDO.getPassword().getBytes()));
		customerDAO.insert(customerDO);
	}

	@Override
	public void update(CustomerDO customerDO) {
		customerDO.setPassword(MD5.getMD5(customerDO.getPassword().getBytes()));
		customerDAO.update(customerDO);
	}

	@Override
	public CustomerDO getById(Long id) {
		return customerDAO.getById(id);
	}

	@Override
	public Result<CustomerDO> login(String mobile, String password) {
		if(StringTools.isAnyEmpty(mobile,password)){
			return Result.newInstance(null, "用户名或密码为空", false);
		}
		CustomerQueryCondition queryCondition = new CustomerQueryCondition();
		queryCondition.setMobile(mobile).setPassword(MD5.getMD5(password.getBytes()));
		List<CustomerDO> list = customerDAO.getByCondition(queryCondition);
		if(list.isEmpty() || list.size() == 0){
			return Result.newInstance(null, "用户名不存在或密码错误", false);
		}
		if(list.size() > 1){
			logger.error("账号异常,发现多个相同的账号:"+mobile);
			return Result.newInstance(null, "账号异常", false);
		}
		return Result.newInstance(list.get(0), "登录成功", true);
	}

	@Override
	public Boolean checkExist(String mobile) {
		CustomerQueryCondition queryCondition = new CustomerQueryCondition();
		queryCondition.setMobile(mobile);
		List<CustomerDO> list = customerDAO.getByCondition(queryCondition);
		return !list.isEmpty();
	}
}
