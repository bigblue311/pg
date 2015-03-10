package com.pg.biz.manager;

import com.pg.dal.model.CustomerDO;

public interface CustomerManager {
	void create(CustomerDO customerDO);
	
	void update(CustomerDO customerDO);
	
	CustomerDO getById(Long id);
	
	CustomerDO login(String mobile, String password);
	
	CustomerDO checkExist(String mobile);
	
	Page
}
