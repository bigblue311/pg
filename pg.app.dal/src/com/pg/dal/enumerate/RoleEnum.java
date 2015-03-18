package com.pg.dal.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum RoleEnum {
	系统管理员(0l,"系统管理员",false),
	管理员(1l,"管理员",true),
	客户经理(2l,"客户经理",true);
	
	private Long code;
	private String desc;
	private boolean editable;
	
	private RoleEnum(Long code,String desc,boolean editable){
		this.code = code;
		this.desc = desc;
		this.editable = editable;
	}

	public Long getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public static List<RoleEnum> getAll(){
		return Lists.newArrayList(RoleEnum.values());
	}
	
	public static List<RoleEnum> getEditable(){
		List<RoleEnum> list = Lists.newArrayList();
		for(RoleEnum role : getAll()){
			if(role.editable){
				list.add(role);
			}
		}
		return list;
	}
	
	public static RoleEnum getByCode(Long code){
		if(code == null){
			return null;
		}
		for(RoleEnum role : getAll()){
			if(role.code.longValue() == code.longValue()){
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
}
