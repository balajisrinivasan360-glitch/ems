package com.tcs.ems.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	public void sendotp(String toEmail,String otp ) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("𝗢𝘁𝗽 𝗙𝗼𝗿 𝗩𝗲𝗿𝗶𝗳𝗶𝗰𝗮𝘁𝗶𝗼𝗻");
		message.setText("𝗪𝗲𝗹𝗰𝗼𝗺𝗲! 𝗬𝗼𝘂𝗿 𝗢𝗧𝗣 𝗳𝗼𝗿 𝗮𝗰𝗰𝗼𝘂𝗻𝘁 𝘃𝗲𝗿𝗶𝗳𝗶𝗰𝗮𝘁𝗶𝗼𝗻 𝗶𝘀 " + otp + ". 𝗜𝘁 𝘄𝗶𝗹𝗹 𝗲𝘅𝗽𝗶𝗿𝗲 𝗶𝗻 𝟭𝟬 𝗺𝗶𝗻𝘂𝘁𝗲𝘀.");
	    
		javaMailSender.send(message);
	}
}
