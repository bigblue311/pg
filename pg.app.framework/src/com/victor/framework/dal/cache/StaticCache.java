package com.victor.framework.dal.cache;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;
import com.victor.framework.dal.basic.EntityDAO;
import com.victor.framework.dal.basic.EntityDO;
import com.victor.framework.dal.basic.QueryCondition;

public abstract class StaticCache<entity extends EntityDO, query extends QueryCondition> extends EntityDAO<entity,query> {
	
	public StaticCache(String namespace) {
		super(namespace);
	}

	public static final String ON = "ON";
	private final Map<String,Object> cacheMap = Maps.newConcurrentMap();
	
	public abstract void reloadCache();
	
	public final void clearCache(){
		cacheMap.clear();
	}
	
	public final Object getCache(String key){
		if(key == null) return null;
		return cacheMap.get(key);
	}
	
	public final void updateCache(String key, Object value){
		cacheMap.put(key, value);
	}
	
	public final void updateDB(entity e){
		super.update(e);
	}
	
	public final void insertDB(entity e){
		super.insert(e);
	}
	
	public final void deleteDB(Long id){
		super.delete(id);
	}
	
	public final Collection<Object> cacheValues(){
		return cacheMap.values();
	}
}
