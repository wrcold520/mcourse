package com.mcourse.frame.db.base.exception;

import com.mcourse.frame.exception.BaseException;

/**
 * 数据库异常类
 * 
 * DBException.java
 *
 * @Description
 * @Created Administrator
 * @DateTime 2017年8月27日下午9:26:11
 */
public class DBException extends BaseException {

	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable cause) {
		super(cause);
	}
}
