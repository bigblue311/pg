package com.pg.dal.dao.impl;

import com.pg.dal.dao.PackageDAO;
import com.pg.dal.model.PackageDO;
import com.pg.dal.query.PackageQueryCondition;
import com.victor.framework.dal.basic.EntityDAO;

public class PackageDAOImpl extends EntityDAO<PackageDO,PackageQueryCondition> implements PackageDAO{

	public PackageDAOImpl() {
		super(PackageDO.class.getSimpleName());
	}

}
