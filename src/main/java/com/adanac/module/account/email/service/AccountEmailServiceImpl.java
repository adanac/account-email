package com.adanac.module.account.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.adanac.framework.log.MyLogger;
import com.adanac.framework.log.MyLoggerFactory;
import com.adanac.module.account.email.exception.AccountEmailException;

public class AccountEmailServiceImpl implements AccountEmailService {

	private MyLogger logger = MyLoggerFactory.getLogger(AccountEmailServiceImpl.class);
	private JavaMailSender javaMailSender;// 邮件发送工具类
	private String systemEmail;

	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
		logger.info("AccountEmailServiceImpl-->sendMail-->to:" + to + " ,subject:" + subject);
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(htmlText, true);
			javaMailSender.send(mimeMessage);
		} catch (AccountEmailException | MessagingException e) {
			logger.error("发送邮件失败:" + e);
			throw new AccountEmailException("Fail to send mail...", e);
		}
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

}
