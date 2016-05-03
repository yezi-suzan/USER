package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String loginUrl = "loginView";
		// 1、请求到登录页面 放行
		if (request.getServletPath().startsWith(loginUrl)) {
			return true;
		}

		// 2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求

		// 3、如果用户已经登录 放行
		if (request.getSession().getAttribute("user") != null) {
			// 更好的实现方式的使用cookie
			return true;
		}

		// 4、非法请求 即这些请求需要登录后才能访问
		// 重定向到登录页面
		response.sendRedirect(request.getContextPath() + loginUrl);
		return false;

	}

}
