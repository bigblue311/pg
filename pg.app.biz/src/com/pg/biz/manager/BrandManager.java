package com.pg.biz.manager;

import java.util.List;

import com.pg.dal.model.BrandDO;

public interface BrandManager {
	
	/**
	 * 
	 * @param brandDO
	 */
	void create(BrandDO brandDO);
	
	/**
	 * 
	 * @param brandDO
	 */
	void update(BrandDO brandDO);
	
	/**
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	BrandDO getById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<BrandDO> getTopLevel();
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	List<BrandDO> getLevel(Long parentId);
}
