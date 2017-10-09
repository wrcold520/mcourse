package com.mcourse.frame.exception;

import com.mcourse.frame.constants.ExceptionConstants;

/**
 * 
 * @Title 工具类的异常
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/16 16:50:42
 */
public class UtilsException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public UtilsException(String message) {
		super(message);
		this.exceptionType = ExceptionConstants.TPYE_UTILS;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UtilsException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = ExceptionConstants.TPYE_UTILS;
	}

}
