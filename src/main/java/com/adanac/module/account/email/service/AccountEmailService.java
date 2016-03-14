package com.adanac.module.account.email.service;

import com.adanac.module.account.email.exception.AccountEmailException;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public interface AccountEmailService {
	/**
	 * 
	 * @param to 接收地址
	 * @param subject 邮件主题
	 * @param htmlText html格式邮件
	 * @throws AccountEmailException
	 */
	void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
