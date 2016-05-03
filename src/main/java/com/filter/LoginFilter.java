package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain fChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest hRequest = (HttpServletRequest) sRequest;
		HttpSession hSession = hRequest.getSession();

		// 排除不需要过滤的的url
		String requestURL = hRequest.getRequestURI();
		if (requestURL.endsWith("login")||requestURL.endsWith("loginView")) {
			fChain.doFilter(sRequest, sResponse);
			return;
		}

		if (null == hSession.getAttribute("user")) {
			((HttpServletResponse) sResponse).sendRedirect("loginView");
		} else {
			fChain.doFilter(sRequest, sResponse);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
