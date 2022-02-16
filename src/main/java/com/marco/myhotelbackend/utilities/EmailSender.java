package com.marco.myhotelbackend.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class EmailSender {

	@Autowired
	private static JavaMailSender javaMailSender;
	

}
