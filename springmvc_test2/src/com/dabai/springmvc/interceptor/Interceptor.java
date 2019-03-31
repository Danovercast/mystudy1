package com.dabai.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("interceptor1 prehand");
		String requestURI=request.getRequestURI();
		if(!requestURI.contains("/login")){
			String username=(String) request.getSession().getAttribute("username");
			if(username==null){
				response.sendRedirect(request.getContextPath()+"/login.action");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView arg3)
			throws Exception {
		System.out.println("interceptor1 posthand");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
		System.out.println("interceptor1 afterCompletion");
		
	}



}
