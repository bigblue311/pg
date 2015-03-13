package com.pg.web.admin.pipeline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;
import com.pg.web.admin.common.AuthenticationToken;
import com.pg.web.admin.common.ExtensionEnum;
import com.pg.web.admin.common.ResourceMap;
import com.victor.framework.common.tools.UriTools;

public class AuthenticationValve implements Valve{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		try {
			String uri = request.getRequestURI();
			String resource = UriTools.getResource(uri);
			String extend = UriTools.getExtension(uri);
			ExtensionEnum extensionEnum = ExtensionEnum.get(extend);
			switch(extensionEnum){
				case JSON:
					//do nothing
					break;
				case HTM:
					if(ResourceMap.loginRequired(resource)){
						if(!AuthenticationToken.logined(session)){
							response.sendRedirect(ResourceMap.LOGIN_URI);
						}
					}
					break;
				default:
					if(ResourceMap.loginRequired(resource)){
						response.sendRedirect(ResourceMap.LOGIN_URI);
					}
					break;
			}
		} finally {
			pipelineContext.invokeNext();
		}
	}
}
