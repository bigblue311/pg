package com.pg.dal.cache;

import com.pg.dal.model.SystemConfigDO;

public interface SystemConfigCache {
	
	/**
	 * 重新加载
	 */
	void reload();
	
	/**
	 * 获取配置
	 * @param key
	 * @return
	 */
	SystemConfigDO getConfig(String key);
	
	/**
	 * 更新数据库
	 * @param systemConfigDO
	 */
	void updateDB(SystemConfigDO systemConfigDO);
	
	/**
	 * 获取开关
	 * @param name
	 * @return
	 */
	boolean getSwitch(String name);
}
