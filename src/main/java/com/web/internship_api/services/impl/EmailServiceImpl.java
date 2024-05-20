package com.web.internship_api.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.web.internship_api.services.EmailService;
@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender mailSender;

    @SuppressWarnings("unused")
	@Autowired
    private Environment env;
	
	@Override
	public void sendMail(String toEmail, String subject, String body) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(toEmail);
        helper.setText(body, true);
        mailSender.send(message);
	}

}
