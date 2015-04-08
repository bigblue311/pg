package com.pg.web.admin.webpage.screen.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.PublishDO;
import com.pg.dal.query.PublishQueryCondition;
import com.victor.framework.common.shared.Result;

public class GetPublish {
	
	@Autowired
	private ProductManager productManager;
	
	public Result<List<PublishDO>> execute() {
		List<PublishDO> list = Lists.newArrayList();
		PublishQueryCondition queryCondition = new PublishQueryCondition();
		list = productManager.getPublishByCondition(queryCondition);
		return Result.newInstance(list, "获取数据成功", !list.isEmpty());
	}
}
