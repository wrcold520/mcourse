package com.mcourse.frame.exception;

import com.mcourse.frame.constants.ExceptionConstants;

/**
 * @Title Service层的异常
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/17 14:36:43
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
		this.exceptionType = ExceptionConstants.TPYE_SERVICE;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = ExceptionConstants.TPYE_SERVICE;
	}

}
