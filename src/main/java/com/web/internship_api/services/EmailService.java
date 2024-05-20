package com.web.internship_api.services;

import javax.mail.MessagingException;

public interface EmailService {
	public void sendMail(String toEmail, String subject, String body) throws MessagingException;
}
