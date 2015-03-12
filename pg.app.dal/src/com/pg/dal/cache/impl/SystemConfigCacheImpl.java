package com.pg.dal.cache.impl;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.pg.dal.cache.SystemConfigCache;
import com.pg.dal.model.SystemConfigDO;
import com.pg.dal.query.SystemConfigQueryCondition;
import com.victor.framework.dal.cache.StaticCache;

public class SystemConfigCacheImpl extends StaticCache<SystemConfigDO,SystemConfigQueryCondition> implements SystemConfigCache{

	public SystemConfigCacheImpl() {
		super(SystemConfigDO.class.getSimpleName());
	}

	@Override
	public void reloadCache() {
		List<SystemConfigDO> list = this.getAll();
		if(list.isEmpty()){
			return;
		}
		for(SystemConfigDO systemConfig : list){
			this.updateCache(systemConfig.getKey(), systemConfig);
		}
	}
	
	@Override
	public SystemConfigDO getConfig(String key){
		SystemConfigDO switchDO = (SystemConfigDO)this.getCache(key);
		return switchDO;
	}
	
	@Override
	public boolean getSwitch(String key) {
		SystemConfigDO switchDO = this.getConfig(key);
		if(switchDO==null){
			return false;
		}
		return ON.equals(switchDO.getValue());
	}

	@Override
	public void reload() {
		reloadCache();
	}

	@Override
	public List<SystemConfigDO> cachedValues() {
		Collection<Object> all = this.cacheValues();
		List<SystemConfigDO> list = Lists.newArrayList();
		for(Object obj : all){
			list.add((SystemConfigDO)obj);
		}
		return list;
	}
}
