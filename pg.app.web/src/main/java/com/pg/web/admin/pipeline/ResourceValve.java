package com.pg.web.admin.pipeline;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pg.dal.enumerate.ExtensionEnum;
import com.pg.dal.enumerate.ResourceEnum;
import com.pg.dal.enumerate.SubMenuEnum;
import com.pg.dal.enumerate.TopMenuEnum;
import com.pg.dal.model.EmployeeDO;
import com.pg.biz.manager.SecurityManager;
import com.pg.web.admin.common.AuthenticationToken;
import com.pg.web.admin.model.json.SubMenuJson;
import com.pg.web.admin.model.json.TopMenuJson;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.common.tools.UriTools;

public class ResourceValve implements Valve{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SecurityManager securityManager;
	
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
			//加载版权信息
			loadSystemCopyright(rundata);
			
			String uri = request.getRequestURI();
			String resource = UriTools.getResource(uri);
			String extend = UriTools.getExtension(uri);
			
			ExtensionEnum extensionEnum = ExtensionEnum.get(extend);
			if(extensionEnum == null){
				if(ResourceEnum.根目录.getResource().equals(resource) ||
				   ResourceEnum.默认页.getResource().equals(resource)	){
					//并且访问页面是需要登录才访问的,那么跳到登录页面
					response.sendRedirect(ResourceEnum.登录.getUri());
				} else {
					if(resource.startsWith(ResourceEnum.根目录.getResource())){
						response.sendRedirect(uri+".htm");
					}
				}
				return;
			}
			switch(extensionEnum){
				case JSON:
					//security check
					break;
				case HTM:
					if(AuthenticationToken.logined(session)){
						EmployeeDO loginedUser = AuthenticationToken.get(session);
						//加载菜单
						ResourceEnum resEnum = ResourceEnum.getByResource(resource);
						SubMenuEnum subMenu = resEnum.getSubMenu();
						TopMenuEnum topMenu = subMenu == null? null : subMenu.getTopMenu();
						generateMenu(rundata,loginedUser,topMenu,subMenu);
						
						rundata.getContext().put("loginedUser", loginedUser);
						if(!securityManager.hasAccess(loginedUser, resource)){
							//直接跳到欢迎页面
							response.sendRedirect(ResourceEnum.没有权限.getUri());
						}
						//加入登录过了,再访问登录页面
						if(resource.equals(ResourceEnum.登录.getResource())){
							//直接跳到欢迎页面
							response.sendRedirect(ResourceEnum.欢迎.getUri());
						}
					} else {
						//没有登录过
						if(ResourceEnum.loginRequired(resource)){
							//并且访问页面是需要登录才访问的,那么跳到登录页面
							response.sendRedirect(ResourceEnum.登录.getUri());
						}
					}
					break;
				default:
					if(ResourceEnum.loginRequired(resource)){
						response.sendRedirect(ResourceEnum.登录.getUri());
					}
					break;
			}
		} finally {
			pipelineContext.invokeNext();
		}
	}
	
	private void loadSystemCopyright(TurbineRunDataInternal rundata){
		//先获取域名
		String domain = "";
		try{
			URL urlObj = new URL(request.getRequestURL().toString());
			domain = urlObj.getHost();
		}catch(Exception e){
			domain = "";
		}
		String year = DateTools.thisYear();
		
		String copyright = "©"+year+"  版权所有"+domain;
		rundata.getContext().put("copyright", copyright);
	}
	
	private void generateMenu(TurbineRunDataInternal rundata,
							  EmployeeDO loginedUser,
							  TopMenuEnum topMenuEnum,
							  SubMenuEnum subMenuEnum){
		List<TopMenuEnum> topMenus = TopMenuEnum.getAll();
		List<TopMenuJson> menus = Lists.newLinkedList();
		for(TopMenuEnum eachTop : topMenus){
			TopMenuJson topMenu = new TopMenuJson();
			
			boolean collapsed = true;
			if(topMenuEnum!=null){
				if(eachTop.getCode().equals(topMenuEnum.getCode())){
					collapsed = false;
				}
			}
			
			topMenu.setText(eachTop.getDesc());
			topMenu.setCollapsed(collapsed);
			List<SubMenuEnum> subMenus = SubMenuEnum.getByTopMenu(eachTop.getCode());
			List<SubMenuJson> items = Lists.newLinkedList();
			for(SubMenuEnum eachSub:subMenus){
				if(securityManager.hasAccess(loginedUser, eachSub.getResource())){
					SubMenuJson subMenu = new SubMenuJson();
					subMenu.setText(eachSub.getDesc());
					subMenu.setHref(eachSub.getUri());
					boolean selected = false;
					if(subMenuEnum!=null){
						if(eachSub.getCode().equals(subMenuEnum.getCode())){
							selected = true;
						}
					}
					subMenu.setSelected(selected);
					items.add(subMenu);
				}
			}
			if(!items.isEmpty()){
				topMenu.setItems(items);
				menus.add(topMenu);
			}
		}
		rundata.getContext().put("menus", JSONObject.toJSONString(menus));
	}
}