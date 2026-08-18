package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.dto.VerifyOtpRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.exception.InvalidOtpException;
import com.tcs.ems.exception.OtpExpiredException;
import com.tcs.ems.exception.UserIsVerifiedException;
import com.tcs.ems.exception.UserNotFoundException;
import com.tcs.ems.repository.UserRepository;

@Service
public class OtpService {
	
	
	private UserRepository userRepository;

	
	public OtpService(UserRepository userRepository) {	
		this.userRepository = userRepository;
	}
	
	
	public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
		
		Optional<User> optionalUser = userRepository.getByEmail(verifyOtpRequest.getEmail());
		
		if(optionalUser.isPresent()) {
				
			User user = optionalUser.get();
			
			if(user.getOtp()==null) {
				
				throw new UserIsVerifiedException(verifyOtpRequest.getEmail()+" : is already verified");
			
			}	
		    if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {	
		    	
		    	throw new InvalidOtpException(verifyOtpRequest.getOtp()+": otp is invalid");
		    	
        	}
		    if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
		    	
		    	throw new OtpExpiredException("Otp is expired enter resend otp");
		    }
		     else {
		    	    user.setVerified(true);
		    	 	user.setOtp(null);
		    	 	user.setOtpexpirytime(null);
		    	 	userRepository.save(user);
			
		    	 	return"otp verified successfully";
		    }
		    }
			 else {
				 
			   throw new UserNotFoundException(verifyOtpRequest.getEmail()+" : is not registerd");
			}
	
	}
	 }
