package com.adanac.module.account.email.exception;

public class AccountEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369680583020194689L;

	public AccountEmailException() {
	};// 无参构造函数

	public AccountEmailException(String message) {
		super(message);// 调用超类构造器
	}

	public AccountEmailException(String string, Exception e) {
		super(string, e);
	}

}
