package com.example.demo.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;

public class LoggingFilter implements Filter {

	String CLIENT = "client";
	String USER_ID = "USER_ID";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 診断コンテキストを設定します.
	 *
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		boolean successfulRegistration = false;

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		Principal principal = req.getUserPrincipal();

		HttpSession session = req.getSession(false);

		if (principal != null) {
			String username = principal.getName();
			successfulRegistration = registerUsername(username);
		}

		try {
			MDC.put(CLIENT, servletRequest.getRemoteAddr());

			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			MDC.remove(CLIENT);
		}

	}

	@Override
	public void destroy() {

	}

	private boolean registerUsername(String username) {
		if (username != null && username.trim().length() > 0) {
			MDC.put(USER_ID, username);
			return true;
		}
		return false;
	}
}
