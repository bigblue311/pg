package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.BrandManager;
import com.pg.dal.model.BrandDO;

public class Brand {
	
	@Autowired
	private BrandManager brandManager;
	
	public void execute(@Param("parentId")Long parentId,
						Context context) {
		
		List<BrandDO> list = Lists.newArrayList();
		if(parentId == null){
			list = brandManager.getTopLevel();
			context.put("parentId", 0l);
		} else {
			list = brandManager.getLevel(parentId);
			List<BrandDO> parentList = Lists.newLinkedList();
			Long prevId = parentId;
			for(int i=0;i<3;i++){
				if(prevId == 0l){
					break;
				} else {
					BrandDO brandDO = brandManager.getById(prevId);
					prevId = brandDO.getParentId();
					parentList.add(brandDO);
				}
			}
			context.put("parentId", parentId);
			parentList = Lists.reverse(parentList);
			context.put("parentList", parentList);
		}
		context.put("list", JSONObject.toJSONString(list));
	}
}
