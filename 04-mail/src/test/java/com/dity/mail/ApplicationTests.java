package com.dity.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private JavaMailSender jms;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testSendMail(){
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setSubject("我是谁");
		mailMessage.setText("我该怎样");
		mailMessage.setFrom("649411629@qq.com");
		mailMessage.setTo("1908715959@qq.com");
		//mailMessage.setBcc("到底是啥");
		//mailMessage.setCc("这又是啥");
		jms.send(mailMessage);
	}

}
