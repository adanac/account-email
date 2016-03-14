package com.adanac.module.account.email;

import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adanac.module.account.email.service.AccountEmailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:account_email.xml" })
public class AccountEmailServiceImplTest2 {

	private GreenMail greenMail;

	@Autowired
	private AccountEmailService accountEmailService;

	@Before
	public void startMailServer() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("", "");
		greenMail.start();
	}

	@Test
	public void testSendMail() throws Exception {
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
