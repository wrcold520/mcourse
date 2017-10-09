package com.mcourse.frame.utils.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title 结果集
 * @Description
 *
 * @Created wangzf
 * @DateTime 2017/06/01 09:01:29
 */
public class Result {
	// 成功的结果类型
	public static final String TYPE_SUCCESS = "success";
	// 失败的结果类型
	public static final String TYPE_FAILED = "failed";
	// 警告
	public static final String TYPE_WARN = "warn";
	// 错误
	public static final String TYPE_ERROR = "error";
	// true
	public static final String TYPE_TRUE = "true";
	// false
	public static final String TYPE_FALSE = "false";

	/** 返回的类型 **/
	private final String type;
	/** 返回的提示信息 **/
	private final String msg;
	/** 返回的参数 **/
	private final Map<String, Object> params;

	private Result(ResultBuilder builder) {
		this.type = builder.type;
		this.msg = builder.msg;
		this.params = builder.params;
	}

	public String getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * ResultBuilder
	 * 
	 * @author wangzf
	 * 
	 */
	public static class ResultBuilder implements IResultBuilder {
		private String type;
		private String msg;
		private Map<String, Object> params = new HashMap<String, Object>();

		/**
		 * @param type
		 */
		public ResultBuilder(String type) {
			super();
			this.type = type;
		}

		public ResultBuilder setMsg(String msg) {
			this.msg = msg;
			return this;
		}

		public ResultBuilder setParams(Map<String, Object> params) {
			this.params = params;
			return this;
		}

		public Result build() {
			Result result = new Result(this);
			return result;
		}
	}
}
