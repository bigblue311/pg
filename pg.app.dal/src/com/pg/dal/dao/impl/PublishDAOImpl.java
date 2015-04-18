package com.pg.dal.dao.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pg.dal.dao.PublishDAO;
import com.pg.dal.model.PackageDO;
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
			Object obj = super.getById(PackageDO.class.getSimpleName(), e.getPackageId());
			if(obj == null){
				continue;
			}
			PackageDO packageDO = (PackageDO)obj;
			String value = packageDO.getName();
				   value+= "    [最小预定量: "+(e.getLimitBuyQuantity()==null?"不限":e.getLimitBuyQuantity())+"箱";
				   value+= "    最小预定额: "+(e.getLimitBuyPrice()==null?"不限":e.getLimitBuyPrice())+"元]";
			map.put(getEnumKey(e), value);
		}
		return map;
	}

}
