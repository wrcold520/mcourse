package com.mcourse.frame.constants;

public class SysConstants {
	// 分页参数
	/** 当前页码 **/
	public static final String PAGE_PARAMS_PAGENO = "page_params_pageno";
	/** 每页显示的条目 **/
	public static final String PAGE_PARAMS_PAGESIZE = "page_params_pagesize";

	/** 数据库主键key名称，这里定义为id **/
	public static final String DB_PRIMARY_KEY = "id";

	// 拦截器相关
	/** 当前请求的method **/
	public static final String INTERCEPTOR_KEY_IP = "interceptor_key_ip";
	/** 当前请求的method **/
	public static final String INTERCEPTOR_KEY_REQUESTMETHOD = "interceptor_key_requestmethod";
	/** 当前请求的method **/
	public static final String INTERCEPTOR_KEY_ISAJAX = "interceptor_key_isajax";
	/** 当前请求的url **/
	public static final String INTERCEPTOR_KEY_REQUESTURL = "interceptor_key_requesturl";
	/** 当前请求url携带的参数 **/
	public static final String INTERCEPTOR_KEY_REQUESTPARAMS = "interceptor_key_requestparams";
	/** 当前请求开始时间 **/
	public static final String INTERCEPTOR_KEY_STARTTIME = "interceptor_key_starttime";
	/** 当前请求结束时间 **/
	public static final String INTERCEPTOR_KEY_ENDTIME = "interceptor_key_endtime";

	/** session中的系统管理员 **/
	public static final String SESSION_KEY_ADMINISTRATOR = "session_key_administrator";
	/** session中的user **/
	public static final String SESSION_KEY_ACTIVEUSER = "sesseion_key_activeuser";

}
