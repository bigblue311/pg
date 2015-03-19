package com.pg.dal.cache.impl;

import org.springframework.beans.factory.InitializingBean;

import com.pg.dal.cache.RoleCache;
import com.pg.dal.model.RoleDO;
import com.pg.dal.query.RoleQueryCondition;
import com.victor.framework.dal.cache.StaticCache;

public class RoleCacheImpl extends StaticCache<RoleDO,RoleQueryCondition> implements RoleCache,InitializingBean{

	public RoleCacheImpl() {
		super(RoleDO.class.getSimpleName());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.reload();
	}

	@Override
	public boolean exist(Long id) {
		return super.exist(id.toString());
	}
}
