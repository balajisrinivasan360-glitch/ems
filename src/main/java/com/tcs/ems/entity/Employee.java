package com.tcs.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {
	@Id
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email cannot be null,empty,space")
	private String email;
	
	@NotBlank(message = "Name cannot be null,empty,space")
	private String name;
	
	@PositiveOrZero     //allows salary with 0 and above
	private Double salary;
	
	@NotBlank(message = "Department cannot be null,empty,space")
	private String department;
}
