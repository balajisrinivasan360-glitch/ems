package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.exception.UserAlreadyExistsException;
import com.tcs.ems.repository.UserRepository;
import com.tcs.ems.util.OtpGenerator;

@Service
public class UserService {
	
	private UserRepository userRepository;
		
	private EmailService emailService;
	
	private PasswordEncoder passwordEncoder;
	



	public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
	}




	public String register(RegisterRequest registerRequest) {
		
		Optional<User> ou= userRepository.getByEmail(registerRequest.getEmail());
		if(ou.isPresent()) {
			
			throw new UserAlreadyExistsException(registerRequest.getEmail()+" : already exists");
		
		}else {
			User user=new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER_ROLE");
			user.setVerified(false);
			
			String otp=OtpGenerator.generateOtp();
			
			user.setOtp(otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(1));     // like it mention that otp expirs in next 10 min of geneartion
			
			userRepository.save(user);
			
			
			emailService.sendotp(registerRequest.getEmail(),otp);
			
			return "Please Enter The Otp For Verification";
		}
	}
	
	
}
