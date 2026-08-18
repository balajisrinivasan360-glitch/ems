package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.dto.ResendOtp;
import com.tcs.ems.entity.User;
import com.tcs.ems.exception.InvalidEmailException;
import com.tcs.ems.exception.UserIsVerifiedException;
import com.tcs.ems.repository.UserRepository;
import com.tcs.ems.util.OtpGenerator;

@Service
public class ResendOtpService {

	private EmailService emailService;
	private UserRepository userRepository;

	
	public ResendOtpService(EmailService emailService, UserRepository userRepository) {
		super();
		this.emailService = emailService;
		this.userRepository = userRepository;
	}


	public String resendOtp(ResendOtp resendOtp) {
		
		Optional<User> optional = userRepository.getByEmail(resendOtp.getEmail());
		if(optional.isPresent()) {
			
			
		User user =	optional.get();
		
		if(user.getOtp()==null) {
			
			throw new UserIsVerifiedException(resendOtp.getEmail()+" : already registerd");
			
		}
		
		String otp=OtpGenerator.generateOtp();
		user.setOtpexpirytime(LocalDateTime.now().plusMinutes(2));
		user.setOtp(otp);
		
		emailService.sendotp(resendOtp.getEmail(),otp);
		userRepository.save(user);
		
		return "otp sent to"+" "+resendOtp.getEmail();
		
		}else {
			
			throw new InvalidEmailException(resendOtp.getEmail()+" : is not registerd email to get verifed");
			
		}
	}
}
