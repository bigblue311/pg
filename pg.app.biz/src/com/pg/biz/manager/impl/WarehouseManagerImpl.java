package com.pg.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.dao.WarehouseDAO;
import com.pg.dal.model.WarehouseDO;
import com.pg.dal.query.WarehouseQueryCondition;
import com.victor.framework.dal.basic.Paging;

public class WarehouseManagerImpl implements WarehouseManager{

	@Autowired
	private WarehouseDAO warehouseDAO;
	
	@Override
	public void create(WarehouseDO warehouseDO) {
		if(warehouseDO.getCustomerId() == null){
			warehouseDO.setCustomerId(0l);
		}
		warehouseDAO.insert(warehouseDO);
	}

	@Override
	public void update(WarehouseDO warehouseDO) {
		warehouseDAO.update(warehouseDO);
	}

	@Override
	public void delete(Long id) {
		warehouseDAO.delete(id);
	}

	@Override
	public WarehouseDO getById(Long id) {
		return warehouseDAO.getById(id);
	}

	@Override
	public List<WarehouseDO> getByCustomerId(Long customerId) {
		WarehouseQueryCondition queryCondition = new WarehouseQueryCondition();
		queryCondition.setCustomerId(customerId);
		return warehouseDAO.getByCondition(queryCondition);
	}

	@Override
	public List<WarehouseDO> getByCondition(WarehouseQueryCondition queryCondition) {
		return warehouseDAO.getByCondition(queryCondition);
	}

	@Override
	public Paging<WarehouseDO> getPage(WarehouseQueryCondition queryCondition) {
		int totalSize = warehouseDAO.getCount(queryCondition);
		@SuppressWarnings("unchecked")
		Paging<WarehouseDO> page = queryCondition.getPaging(totalSize, 5);
		List<WarehouseDO> list = warehouseDAO.getPage(queryCondition);
		page.setData(list);
		return page;
	}

}
