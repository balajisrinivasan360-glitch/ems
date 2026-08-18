package com.tcs.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ems.entity.Employee;
import com.tcs.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(@RequestBody EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public String cretaeEmployee(@Valid	@RequestBody Employee employee) {
		return employeeService.cretaeEmployee(employee);
	}
	
	@GetMapping("/{email}")
	public Object fetchEmployeeByEmail(@PathVariable String email) {
	return employeeService.fetchEmployeeByEmail(email);
	}
	
	@GetMapping
	public List<Employee> fetchAllEmployees(){
	return employeeService.fetchAllEmployees();
	}
	
	@DeleteMapping("/{email}")
	public String deleteEmployeeByEmail(@PathVariable String email) {
		return employeeService.deleteEmployeeByEmail(email);
	}
	
	@PutMapping("/{email}")
	public String updateEmployeeByEmail(@RequestBody Employee employee,@PathVariable String email) {
		return employeeService.updatePutEmployeeByEmail(employee,email);
	}
	
	@PatchMapping("/{email}")
	public String updatePatchEmployeeByEmail(@RequestBody Employee employee,@PathVariable String email) {
		return employeeService.updatePatchEmployeeByEmail(employee,email);
	}
}
