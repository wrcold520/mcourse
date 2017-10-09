package com.mcourse.frame.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title 自定义异常的超类
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/17 14:09:29
 */
public abstract class BaseException extends RuntimeException {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 1L;

	/** 异常类型 **/
	protected String exceptionType;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		logger.error("[ Exception ] Msg: " + message + ", Cause: " + cause.toString());
		cause.printStackTrace();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		logger.error("[ Exception ] Msg: " + message + ", Cause: " + cause.toString());
		cause.printStackTrace();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
		logger.error("[ Exception ] Cause: " + cause.toString());
		cause.printStackTrace();
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}
