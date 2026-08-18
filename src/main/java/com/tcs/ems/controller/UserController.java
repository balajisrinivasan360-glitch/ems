package com.tcs.ems.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.dto.ResendOtp;
import com.tcs.ems.dto.VerifyOtpRequest;
import com.tcs.ems.service.OtpService;
import com.tcs.ems.service.ResendOtpService;
import com.tcs.ems.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private UserService userService;
	private OtpService otpService;
	private ResendOtpService resendOtpService;


	public UserController(UserService userService, OtpService otpService, ResendOtpService resendOtpService) {
		super();
		this.userService = userService;
		this.otpService = otpService;
		this.resendOtpService = resendOtpService;
	}

	
	@PostMapping("/register")
	public String userRegister(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String VerifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return	otpService.VerifyOtp(verifyOtpRequest);
	}
	
	@PostMapping("/resend-otp")
	public String resendOtp(@RequestBody ResendOtp resendOtp) {
		return resendOtpService.resendOtp(resendOtp);				
	}
}
