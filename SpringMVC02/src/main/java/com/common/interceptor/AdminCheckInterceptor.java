package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.model.UserVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		HttpSession ses = req.getSession();
		
		UserVO user = (UserVO)ses.getAttribute("loginUser");
		
		if(user== null || user.getStatus() != 9) {
			String view = "/WEB-INF/views/msg.jsp";
			
			req.setAttribute("message", "관리자만 이용 가능합니다");
			req.setAttribute("loc", req.getContextPath()+"/index");
			
			RequestDispatcher disp = req.getRequestDispatcher(view);
			disp.forward(req, res);
			
			return false;
		}
		return true;
	}
}
