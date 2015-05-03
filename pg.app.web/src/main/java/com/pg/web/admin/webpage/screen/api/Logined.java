package com.pg.web.admin.webpage.screen.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.web.admin.common.AuthenticationToken;
import com.victor.framework.common.shared.Result;

public class Logined {
	
	@Autowired
    private HttpSession session;
	
	public Result<Boolean> execute() {
		boolean logined = AuthenticationToken.customerLogined(session);
		return Result.newInstance(logined, "", logined);
	}
}
