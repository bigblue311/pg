package com.pg.web.admin.webpage.screen;

import java.util.List;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pg.dal.enumerate.ResourceEnum;
import com.pg.dal.enumerate.RoleEnum;
import com.pg.web.admin.model.json.CrumbJson;
import com.pg.web.admin.model.json.RoleJson;

public class Role {
	
	public void execute(Context context) {
		setCrumb(context);
		List<RoleEnum> list = RoleEnum.getEditable();
		context.put("list", JSONObject.toJSONString(toJson(list)));
	}
	
	private List<RoleJson> toJson(List<RoleEnum> list){
		if(list == null || list.isEmpty()){
			return Lists.newArrayList();
		}
		return Lists.transform(list, new Function<RoleEnum,RoleJson>(){

			@Override
			public RoleJson apply(RoleEnum roleEnum) {
				RoleJson json = new RoleJson();
				json.setId(roleEnum.getCode());
				json.setName(roleEnum.getDesc());
				return json;
			}
			
		});
	}
	
	private void setCrumb(Context context){
		List<CrumbJson> crumbs = Lists.newLinkedList();
		crumbs.add(new CrumbJson(ResourceEnum.角色权限.getName(),ResourceEnum.角色权限.getUri()));
		context.put("crumbs", crumbs);
	}
}
