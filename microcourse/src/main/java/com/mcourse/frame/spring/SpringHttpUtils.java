package com.mcourse.frame.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringHttpUtils {
	/**
	 * 获取当前的request
	 * 
	 * @return
	 */
	public static HttpServletRequest getCurrntRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取当前session
	 * 
	 * @return
	 */
	public static HttpSession getCurrentSession() {
		return getCurrntRequest().getSession();
	}
}
