package com.mcourse.frame.exception;

import com.mcourse.frame.constants.ExceptionConstants;

/**
 * @Title Dao层的异常
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/17 14:09:20
 */
public class DaoException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
		this.exceptionType = ExceptionConstants.TPYE_DAO;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = ExceptionConstants.TPYE_DAO;
	}

}
