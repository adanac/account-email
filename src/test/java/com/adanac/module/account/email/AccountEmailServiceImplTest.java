package com.adanac.module.account.email;

import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adanac.module.account.email.service.AccountEmailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

import junit.framework.Assert;

public class AccountEmailServiceImplTest {

	private GreenMail greenMail;

	@Before
	public void startMailServer() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("", "");
		greenMail.start();
	}

	@Test
	public void testSendMail() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("account_email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) context.getBean("accountEmailService");
		String to = "";
		String subject = "";
		String htmlText = "";
		accountEmailService.sendMail(to, subject, htmlText);
		greenMail.waitForIncomingEmail(2000, 1);// 接收一封邮件最多等待2s
		MimeMessage[] messages = greenMail.getReceivedMessages();
		Assert.assertEquals(1, messages.length);
		Assert.assertEquals(subject, messages[0].getSubject());
		Assert.assertEquals(htmlText, GreenMailUtil.getBody(messages[0]).trim());

	}

	@After
	public void stopMailServer() throws Exception {
		greenMail.stop();
	}

}
