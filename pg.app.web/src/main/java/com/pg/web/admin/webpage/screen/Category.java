package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.CategoryManager;
import com.pg.dal.model.CategoryDO;

public class Category {
	
	@Autowired
	private CategoryManager CategoryManager;
	
	public void execute(@Param("parentId")Long parentId,
						Context context) {
		
		List<CategoryDO> list = Lists.newArrayList();
		if(parentId == null){
			list = CategoryManager.getTopLevel();
			context.put("parentId", 0l);
		} else {
			list = CategoryManager.getLevel(parentId);
			List<CategoryDO> parentList = Lists.newLinkedList();
			Long prevId = parentId;
			for(int i=0;i<3;i++){
				if(prevId == 0l){
					break;
				} else {
					CategoryDO CategoryDO = CategoryManager.getById(prevId);
					prevId = CategoryDO.getParentId();
					parentList.add(CategoryDO);
				}
			}
			context.put("parentId", parentId);
			parentList = Lists.reverse(parentList);
			context.put("parentList", parentList);
		}
		context.put("list", JSONObject.toJSONString(list));
	}
}
