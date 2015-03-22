package com.pg.dal.cache;

import java.util.List;

import com.pg.dal.model.LocationDO;

public interface LocationCache {
	
	/**
	 * 获取所有
	 * @return
	 */
	List<LocationDO> cacheValues();
	
	/**
	 * 重新加载
	 */
	void reload();
	
	/**
	 * 获取配置
	 * @param key
	 * @return
	 */
	LocationDO getCache(String key);
}
