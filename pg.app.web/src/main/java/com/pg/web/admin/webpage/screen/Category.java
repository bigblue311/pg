package com.pg.web.admin.webpage.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.biz.manager.CategoryManager;
import com.pg.dal.model.CategoryDO;
import com.pg.web.admin.common.CrumbLink;
import com.pg.web.admin.enumerate.ResourceEnum;

public class Category {
	
	@Autowired
	private CategoryManager categoryManager;
	
	public void execute(@Param("parentId")Long parentId,
						Context context) {
		setCrumb(context,parentId);
		List<CategoryDO> list = Lists.newArrayList();
		if(parentId == null){
			list = categoryManager.getTopLevel();
			context.put("parentId", 0l);
		} else {
			list = categoryManager.getLevel(parentId);
			context.put("parentId", parentId);
		}
		context.put("list", JSONObject.toJSONString(list));
	}
	
	private void setCrumb(Context context,Long parentId){
		List<CrumbLink> crumbs = Lists.newLinkedList();
		Long prevId = parentId;
		for(int i=0;i<3;i++){
			if(prevId == null || prevId == 0l){
				break;
			} else {
				CategoryDO categoryDO = categoryManager.getById(prevId);
				crumbs.add(new CrumbLink(categoryDO.getName(),ResourceEnum.品类管理.getUri()+"?parentId="+prevId));
				prevId = categoryDO.getParentId();
			}
		}
		crumbs.add(new CrumbLink(ResourceEnum.品类管理.getDesc(),ResourceEnum.品类管理.getUri()));
		crumbs = Lists.reverse(crumbs);
		context.put("crumbs", crumbs);
	}
}
