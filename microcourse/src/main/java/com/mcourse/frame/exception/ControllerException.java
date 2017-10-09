package com.mcourse.frame.exception;

import com.mcourse.frame.constants.ExceptionConstants;

/**
 * @Title Controller层的异常
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/17 14:40:01
 */
public class ControllerException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ControllerException(String message) {
		super(message);
		this.exceptionType = ExceptionConstants.TPYE_CONTROLLER;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = ExceptionConstants.TPYE_CONTROLLER;
	}
}
