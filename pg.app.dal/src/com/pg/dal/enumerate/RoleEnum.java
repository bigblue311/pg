package com.pg.dal.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum RoleEnum {
	系统管理员("0","系统管理员"),
	管理员("1","管理员"),
	客户经理("2","客户经理");
	
	private String code;
	private String desc;
	
	private RoleEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static List<RoleEnum> getAll(){
		return Lists.newArrayList(RoleEnum.values());
	}
	
	public static RoleEnum getByCode(String code){
		for(RoleEnum role : getAll()){
			if(role.code.equals(code)){
				return role;
			}
		}
		return null;
	}
	
	public static RoleEnum getByDesc(String desc){
		for(RoleEnum role : getAll()){
			if(role.desc.equals(desc)){
				return role;
			}
		}
		return null;
	}
	
	public static RoleEnum getByText(String text){
		if(getByCode(text) == null) {
			return getByDesc(text);
		}
		return getByCode(text);
	}
}
