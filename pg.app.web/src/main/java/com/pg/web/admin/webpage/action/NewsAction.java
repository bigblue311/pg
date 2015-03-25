package com.pg.web.admin.webpage.action;

import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.web.admin.common.SystemNews;
import com.pg.web.admin.model.form.NewsFO;
import com.victor.framework.common.tools.DateTools;

public class NewsAction {
	
	public void doUpdate(@FormGroup("systemNews") NewsFO newsFO) {
		if(newsFO.getId() == null ) {
			Long id = DateTools.getRandomId();
			newsFO.setId(id);
		}
		SystemNews.putNews(newsFO);
	}
	
	public void doDelete(@FormGroup("systemNews") NewsFO newsFO) {
		SystemNews.delNews(newsFO);
	}
}
