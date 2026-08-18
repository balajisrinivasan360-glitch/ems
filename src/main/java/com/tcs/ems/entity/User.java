package com.tcs.ems.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotNull
	private String name;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@Size(min = 6)
	private String password;
	
	private String role;
	
	private boolean verified;
	
	private String otp;
	
	private LocalDateTime otpexpirytime;
}
