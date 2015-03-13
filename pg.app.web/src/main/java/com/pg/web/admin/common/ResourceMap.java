package com.pg.web.admin.common;

import java.util.Map;

import com.google.common.collect.Maps;

public class ResourceMap {
	/**
	 * key: resource
	 * value: true  - login required; 
	 * 		  false - login not required;
	 */
	private static final Map<String,Boolean> loginMap = Maps.newHashMap();
	
	public static final String LOGIN_URI = "/admin/login.htm";
	
	static{
		loginMap.put("/admin/welcome", 	true);
		loginMap.put("/admin/login", 	false);
	}
	
	public static boolean loginRequired(String resource){
		Boolean loginRequired = loginMap.get(resource);
		if(loginRequired != null){
			return loginRequired;
		} else {
			if(resource.startsWith("/admin")){
				return true;
			} else {
				return false;
			}
		}
	}
}
