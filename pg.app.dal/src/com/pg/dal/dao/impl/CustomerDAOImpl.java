package com.pg.dal.dao.impl;

import com.pg.dal.dao.CustomerDAO;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.query.CustomerQueryCondition;
import com.victor.framework.dal.basic.EntityDAO;

public class CustomerDAOImpl extends EntityDAO<CustomerDO,CustomerQueryCondition> implements CustomerDAO{

	public CustomerDAOImpl() {
		super(CustomerDO.class.getSimpleName());
	}
}
