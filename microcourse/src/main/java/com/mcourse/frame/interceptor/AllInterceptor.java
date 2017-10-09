package com.mcourse.frame.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mcourse.frame.constants.SysConstants;
import com.mcourse.frame.utils.RequestUtils;
import com.mcourse.frame.utils.SysPropUtils;
import com.mcourse.frame.utils.date.DatePattern;
import com.mcourse.frame.utils.date.DateUtils;
import com.mcourse.frame.utils.json.JsonUtils;

/**
 * spring mvc 全局的拦截器
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月5日下午5:31:38
 */
public class AllInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AllInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Date startTime = new Date();// 记录请求的开始时间
		String ip = RequestUtils.getIpAddress(request);// 访问来源ip
		String requestMethod = request.getMethod();// get post
		Boolean isAjax = RequestUtils.isAjax(request);// 是否是ajax
		String requestUrl = request.getRequestURL().toString();// 记录请求的url
		Map<String, String[]> paramMap = request.getParameterMap();// 请求的参数

		request.setAttribute(SysConstants.INTERCEPTOR_KEY_STARTTIME, startTime);
		request.setAttribute(SysConstants.INTERCEPTOR_KEY_IP, ip);
		request.setAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTMETHOD, requestMethod);
		request.setAttribute(SysConstants.INTERCEPTOR_KEY_ISAJAX, isAjax);
		request.setAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTURL, requestUrl);
		request.setAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTPARAMS, JsonUtils.stringify(paramMap));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Date endTime = new Date();
		Date startTime = (Date) request.getAttribute(SysConstants.INTERCEPTOR_KEY_STARTTIME);
		String ip = (String) request.getAttribute(SysConstants.INTERCEPTOR_KEY_IP);
		String requestMethod = (String) request.getAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTMETHOD);
		Boolean isAjax = (Boolean) request.getAttribute(SysConstants.INTERCEPTOR_KEY_ISAJAX);
		String requestURL = (String) request.getAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTURL);
		String paramsJson = (String) request.getAttribute(SysConstants.INTERCEPTOR_KEY_REQUESTPARAMS);

		StringBuffer logstr = new StringBuffer();
		logstr.append(SysPropUtils.LINE_SEPARATOR + "-------- All Interceptor Log Request --------");
		logstr.append(SysPropUtils.LINE_SEPARATOR + "-------- From: " + ip);
		logstr.append(SysPropUtils.LINE_SEPARATOR + "-------- RequestMethod: " + requestMethod + ", isAjax: " + isAjax);
		logstr.append(SysPropUtils.LINE_SEPARATOR + "-------- RequestURL: " + requestURL);
		logstr.append(SysPropUtils.LINE_SEPARATOR + "-------- Params: " + paramsJson);
		logstr.append(
				SysPropUtils.LINE_SEPARATOR + "-------- TimeConsuming: " + (endTime.getTime() - startTime.getTime())
						+ " ms , StartTime: " + DateUtils.format(startTime, DatePattern.DATETIME_SLASH) + " , EndTime: "
						+ DateUtils.format(endTime, DatePattern.DATETIME_SLASH));

		logger.info(logstr.toString());
	}

}
