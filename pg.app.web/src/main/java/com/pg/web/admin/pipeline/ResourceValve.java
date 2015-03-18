package com.pg.web.admin.pipeline;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.pg.dal.model.EmployeeDO;
import com.pg.web.admin.common.AuthenticationToken;
import com.pg.web.admin.enumerate.ExtensionEnum;
import com.pg.web.admin.enumerate.ResourceEnum;
import com.pg.web.admin.enumerate.SubMenuEnum;
import com.pg.web.admin.enumerate.TopMenuEnum;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.common.tools.UriTools;

public class ResourceValve implements Valve{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
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
					//加载菜单
					ResourceEnum resEnum = ResourceEnum.getByResource(resource);
					SubMenuEnum subMenu = resEnum.getSubMenu();
					TopMenuEnum topMenu = subMenu == null? null : subMenu.getTopMenu();
					rundata.getContext().put("subMenu", subMenu == null ? "":subMenu.getCode());
					rundata.getContext().put("topMenu", topMenu == null ? "":topMenu.getCode());
					
					if(AuthenticationToken.logined(session)){
						EmployeeDO loginedUser = AuthenticationToken.get(session);
						rundata.getContext().put("loginedUser", loginedUser);
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
}
