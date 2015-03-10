package com.pg.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.biz.manager.CategoryManager;
import com.pg.dal.dao.CategoryDAO;
import com.pg.dal.model.CategoryDO;
import com.pg.dal.query.CategoryQueryCondition;

public class CategoryManagerImpl implements CategoryManager{

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public void create(CategoryDO CategoryDO) {
		categoryDAO.insert(CategoryDO);
	}

	@Override
	public void update(CategoryDO CategoryDO) {
		categoryDAO.update(CategoryDO);
	}

	@Override
	public void delete(Long id) {
		categoryDAO.softDelete(id);
	}

	@Override
	public CategoryDO getById(Long id) {
		return categoryDAO.getById(id);
	}

	@Override
	public List<CategoryDO> getTopLevel() {
		CategoryQueryCondition queryCondition = new CategoryQueryCondition();
		queryCondition.setParentId(-1l);
		return categoryDAO.getByCondition(queryCondition);
	}

	@Override
	public List<CategoryDO> getLevel(Long parentId) {
		CategoryQueryCondition queryCondition = new CategoryQueryCondition();
		queryCondition.setParentId(parentId);
		return categoryDAO.getByCondition(queryCondition);
	}

}