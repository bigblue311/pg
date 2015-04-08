package com.pg.dal.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum ProdTypeEnum {
	产品("0","产品"),
	产品包("1","产品包");
	
	private String code;
	private String desc;
	
	private ProdTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static List<ProdTypeEnum> getAll(){
		return Lists.newArrayList(ProdTypeEnum.values());
	}
	
	public static ProdTypeEnum getByCode(String code){
		for(ProdTypeEnum prodType : getAll()){
			if(prodType.code.equals(code)){
				return prodType;
			}
		}
		return null;
	}
	
	public static ProdTypeEnum getByDesc(String desc){
		for(ProdTypeEnum prodType : getAll()){
			if(prodType.desc.equals(desc)){
				return prodType;
			}
		}
		return null;
	}
	
	public static ProdTypeEnum getByText(String text){
		if(getByCode(text) == null) {
			return getByDesc(text);
		}
		return getByCode(text);
	}
}
