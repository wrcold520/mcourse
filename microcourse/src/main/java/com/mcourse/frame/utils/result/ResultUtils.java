package com.mcourse.frame.utils.result;

import java.util.Map;

import com.mcourse.frame.exception.UtilsException;
import com.mcourse.frame.utils.json.JsonUtils;
import com.mcourse.frame.utils.result.Result.ResultBuilder;

/**
 * @Title ajax result结果集
 * @Description
 *
 * @Created DarkRanger
 * @DateTime 2017/05/16 21:42:05
 */
public class ResultUtils {

	/** 构造自定义ResultBuildr **/
	private static ResultBuilder getResultBuilder(String type) {
		return new ResultBuilder(type);
	}

	// 构造自定义的结果
	public static Result build(String type, String msg) {
		return getResultBuilder(type).setMsg(msg).build();
	}

	// 构造自定义的结果，携带一个参数
	public static Result build(String type, String msg, Map<String, Object> params) {
		return getResultBuilder(type).setMsg(msg).setParams(params).build();
	}

	/**
	 * 构建操作成功的Result
	 * 
	 * @return
	 */
	public static Result buildSuccess() {
		return getResultBuilder(Result.TYPE_SUCCESS).setMsg("操作成功！").build();
	}

	/**
	 * 构建操作成功的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @return
	 */
	public static Result buildSuccess(String msg) {
		return getResultBuilder(Result.TYPE_SUCCESS).setMsg(msg).build();
	}

	/**
	 * 构建操作成功的Result
	 * 
	 * @param params
	 *            参数
	 * @return
	 */
	public static Result buildSuccess(Map<String, Object> params) {
		return getResultBuilder(Result.TYPE_SUCCESS).setMsg("操作成功！").setParams(params).build();
	}

	/**
	 * 构建操作成功的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @param params
	 *            参数
	 * @return
	 */
	public static Result buildSuccess(String msg, Map<String, Object> params) {
		return getResultBuilder(Result.TYPE_SUCCESS).setMsg(msg).setParams(params).build();
	}

	/**
	 * 构建操作失败的Result
	 * 
	 * @return
	 */
	public static Result buildFailed() {
		return getResultBuilder(Result.TYPE_FAILED).setMsg("抱歉，操作失败！").build();
	}

	/**
	 * 构建操作失败的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @return
	 */
	public static Result buildFailed(String msg) {
		return getResultBuilder(Result.TYPE_FAILED).setMsg(msg).build();
	}

	/**
	 * 构建操作失败的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @param params
	 *            参数
	 * @return
	 */
	public static Result buildFailed(String msg, Map<String, Object> params) {
		return getResultBuilder(Result.TYPE_FAILED).setMsg(msg).setParams(params).build();
	}

	/**
	 * 构建系统异常的Result
	 * 
	 * @return
	 */
	public static Result buildError() {
		return getResultBuilder(Result.TYPE_ERROR).setMsg("抱歉，系统异常！").build();
	}

	/**
	 * 构建系统异常的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @return
	 */
	public static Result buildError(String msg) {
		return getResultBuilder(Result.TYPE_ERROR).setMsg(msg).build();
	}

	/**
	 * 构建操作失败的Result
	 * 
	 * @param msg
	 *            提示信息
	 * @param params
	 *            参数
	 * @return
	 */
	public static Result buildError(String msg, Map<String, Object> params) {
		return getResultBuilder(Result.TYPE_ERROR).setMsg(msg).setParams(params).build();
	}

	// 构造自定义的结果
	public static String buildJson(String type, String msg) {
		return resultToJson(build(type, msg));
	}

	// 构造自定义的结果，携带一个参数
	public static String buildJson(String type, String msg, Map<String, Object> params) {
		return resultToJson(build(type, msg, params));
	}

	// 将Result结果转化为json字符串
	private static String resultToJson(Result result) {
		try {
			return JsonUtils.stringify(result);
		} catch (Exception e) {
			throw new UtilsException("AjaxResult转换json发生异常", e);
		}
	}
}
