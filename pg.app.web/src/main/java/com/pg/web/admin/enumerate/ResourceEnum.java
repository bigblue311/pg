package com.pg.web.admin.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum ResourceEnum {
	
	根目录("-2","/admin","根目录",null,false),
	默认页("-1","/admin/","默认页",null,false),
	登录("0","/admin/login","登录页面",null,false),
	欢迎("1","/admin/welcome","欢迎页面",null,true);
	
	private ResourceEnum(String code,String resource,String desc, SubMenuEnum subMenu, Boolean loginRequired){
		this.code = code;
		this.resource = resource;
		this.desc = desc;
		this.subMenu = subMenu;
		this.loginRequired = loginRequired;
	}
	
	private String code;
	private String resource;
	private String desc;
	private SubMenuEnum subMenu;
	private Boolean loginRequired;
	
	public String getCode() {
		return code;
	}
	public String getUri(){
		return resource+".htm";
	}
	public String getResource() {
		return resource;
	}
	public String getDesc() {
		return desc;
	}
	public SubMenuEnum getSubMenu() {
		return subMenu;
	}
	public Boolean getLoginRequired() {
		return loginRequired;
	}
	
	public static List<ResourceEnum> getAll(){
		return Lists.newArrayList(ResourceEnum.values());
	}
	
	public static ResourceEnum getByCode(String code){
		for(ResourceEnum res : getAll()){
			if(res.code.equals(code)){
				return res;
			}
		}
		return null;
	}
	
	public static ResourceEnum getByResource(String resource){
		for(ResourceEnum res : getAll()){
			if(res.resource.equals(resource)){
				return res;
			}
		}
		return null;
	}
	
	public static boolean loginRequired(String resource){
		ResourceEnum resourceEnum = getByResource(resource);
		if(resourceEnum != null){
			return resourceEnum.loginRequired;
		} else {
			if(resource.startsWith("/admin")){
				return true;
			} else {
				return false;
			}
		}
	}
}
