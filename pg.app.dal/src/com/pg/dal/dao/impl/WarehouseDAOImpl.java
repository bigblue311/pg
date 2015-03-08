package com.pg.dal.dao.impl;

import com.pg.dal.dao.WarehouseDAO;
import com.pg.dal.model.WarehouseDO;
import com.pg.dal.query.WarehouseQueryCondition;
import com.victor.framework.dal.basic.EntityDAO;

public class WarehouseDAOImpl extends EntityDAO<WarehouseDO,WarehouseQueryCondition> implements WarehouseDAO{

	public WarehouseDAOImpl() {
		super(WarehouseDO.class.getSimpleName());
	}

}
