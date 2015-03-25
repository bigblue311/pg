package com.pg.web.admin.common;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pg.web.admin.model.form.NewsFO;

public class SystemNews {
	private static Map<Long,NewsFO> newsMap = Maps.newLinkedHashMap();
	
	public static void putNews(NewsFO newsFO){
		newsMap.put(newsFO.getId(), newsFO);
	}
	
	public static void delNews(NewsFO newsFO){
		newsMap.remove(newsFO.getId());
	}
	
	public static Collection<NewsFO> getAll(){
		return newsMap.values();
	}
}
