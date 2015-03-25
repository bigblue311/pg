package com.pg.dal.dao.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
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
	
	public Map<String,String> getEnumMap(){
		Map<String,String> map = Maps.newLinkedHashMap();
		List<PublishDO> list = this.getByCondition(new PublishQueryCondition());
		for(PublishDO e: list){
			String value = e.getName();
				   value+= "    [库存: "+(e.getBalance()==null?"不限":e.getBalance());
				   value+= "    最小预定量: "+(e.getLimitBuy()==null?"不限":e.getLimitBuy())+"]";
			map.put(getEnumKey(e), value);
		}
		return map;
	}

}
