package com.pg.dal.dao.impl;

import com.pg.dal.dao.PublishDAO;
import com.pg.dal.model.PublishDO;
import com.pg.dal.query.PublishQueryCondition;
import com.victor.framework.dal.basic.EntityDAO;

public class PublishDAOImpl extends EntityDAO<PublishDO,PublishQueryCondition> implements PublishDAO{

	public PublishDAOImpl() {
		super(PublishDO.class.getSimpleName());
	}

	@Override
	public Boolean deleteByPackageId(Long packageId) {
		return super.deleteBySID("deleteByPackageId", packageId);
	}

	@Override
	public Boolean deleteByProductId(Long productId) {
		return super.deleteBySID("deleteByProductId", productId);
	}

}
