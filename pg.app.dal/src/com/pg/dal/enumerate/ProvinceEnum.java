package com.pg.dal.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum ProvinceEnum {
	
	北京市1(1,"北京市"),

	天津市22(22,"天津市"),

	上海市44(44,"上海市"),

	重庆市66(66,"重庆市"),

	河北省108(108,"河北省"),

	山西省406(406,"山西省"),

	内蒙古622(622,"内蒙古"),

	辽宁省804(804,"辽宁省"),

	吉林省945(945,"吉林省"),

	黑龙江省1036(1036,"黑龙江省"),

	江苏省1226(1226,"江苏省"),

	浙江省1371(1371,"浙江省"),

	安徽省1500(1500,"安徽省"),

	福建省1679(1679,"福建省"),

	江西省1812(1812,"江西省"),

	山东省1992(1992,"山东省"),

	河南省2197(2197,"河南省"),

	湖北省2456(2456,"湖北省"),

	湖南省2613(2613,"湖南省"),

	广东省2822(2822,"广东省"),

	广西3015(3015,"广西"),

	海南省3201(3201,"海南省"),

	四川省3235(3235,"四川省"),

	贵州省3561(3561,"贵州省"),

	云南省3728(3728,"云南省"),

	西藏3983(3983,"西藏"),

	陕西省4136(4136,"陕西省"),

	甘肃省4334(4334,"甘肃省"),

	青海省4499(4499,"青海省"),

	宁夏4588(4588,"宁夏"),

	新疆4624(4624,"新疆"),

	香港4802(4802,"香港"),

	澳门4822(4822,"澳门"),

	台湾省4825(4825,"台湾省");
	
	private int code;
	private String name;
	
	private ProvinceEnum(int code,String name){
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static List<ProvinceEnum> getAll(){
		return Lists.newArrayList(ProvinceEnum.values());
	}
	
	public static ProvinceEnum getByCode(int code){
		for(ProvinceEnum location : getAll()){
			if(location.code == code){
				return location;
			}
		}
		return null;
	}
	
	public static ProvinceEnum getByName(String name){
		for(ProvinceEnum location : getAll()){
			if(location.name.equals(name)){
				return location;
			}
		}
		return null;
	}
}
