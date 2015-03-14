package com.pg.web.admin.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum SubMenuEnum {
	
	后台账号("subMenuEmployee","后台账号",TopMenuEnum.系统管理),
	资源权限("subMenuResource","资源权限",TopMenuEnum.系统管理),
	系统配置("subMenuSystem","系统配置",TopMenuEnum.系统管理),
	
	品牌管理("subMenuBrand","品牌管理",TopMenuEnum.产品管理),
	品类管理("subMenuCategory","品类管理",TopMenuEnum.产品管理),
	产品管理("subMenuProduct","产品管理",TopMenuEnum.产品管理),
	产品打包("subMenuPackage","产品打包",TopMenuEnum.产品管理),
	
	客户查询("subMenuCustomer","客户查询",TopMenuEnum.客户管理),
	
	我的仓库("subMenuMyWarehouse","我的仓库",TopMenuEnum.物流管理),
	客户仓库("subMenuCuWarehouse","客户仓库",TopMenuEnum.物流管理),
	
	商品发布("subMenuPublish","商品发布",TopMenuEnum.交易管理),
	订单管理("subMenuOrder","订单管理",TopMenuEnum.交易管理);
	
	private SubMenuEnum(String code,String desc,TopMenuEnum topMenu){
		this.code = code;
		this.desc = desc;
		this.topMenu = topMenu;
	}
	
	private String code;
	private String desc;
	private TopMenuEnum topMenu;
	
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public TopMenuEnum getTopMenu(){
		return topMenu;
	}
	
	public static List<SubMenuEnum> getAll(){
		return Lists.newArrayList(SubMenuEnum.values());
	}
	
	public static SubMenuEnum getByCode(String code){
		for(SubMenuEnum menu : getAll()){
			if(menu.code.equals(code)){
				return menu;
			}
		}
		return null;
	}
	
	public static SubMenuEnum getByDesc(String desc){
		for(SubMenuEnum menu : getAll()){
			if(menu.desc.equals(desc)){
				return menu;
			}
		}
		return null;
	}
	
	public static SubMenuEnum getByText(String text){
		if(getByCode(text) == null) {
			return getByDesc(text);
		}
		return getByCode(text);
	}
}
