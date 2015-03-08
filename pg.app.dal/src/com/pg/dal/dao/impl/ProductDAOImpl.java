package com.pg.dal.dao.impl;

import com.pg.dal.dao.ProductDAO;
import com.pg.dal.model.ProductDO;
import com.pg.dal.query.ProductQueryCondition;
import com.victor.framework.dal.basic.EntityDAO;

public class ProductDAOImpl extends EntityDAO<ProductDO,ProductQueryCondition> implements ProductDAO{

	public ProductDAOImpl() {
		super(ProductDO.class.getSimpleName());
	}

}
