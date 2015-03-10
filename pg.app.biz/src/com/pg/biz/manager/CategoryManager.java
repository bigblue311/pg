package com.pg.biz.manager;

import java.util.List;

import com.pg.dal.model.CategoryDO;

public interface CategoryManager {
	
	/**
	 * 
	 * @param CategoryDO
	 */
	void create(CategoryDO categoryDO);
	
	/**
	 * 
	 * @param CategoryDO
	 */
	void update(CategoryDO categoryDO);
	
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
	CategoryDO getById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<CategoryDO> getTopLevel();
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	List<CategoryDO> getLevel(Long parentId);
}
