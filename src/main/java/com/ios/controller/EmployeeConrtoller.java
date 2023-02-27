package com.ios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ios.exception.EmployeeNotFoundExceptoin;
import com.ios.exception.WrongInputException;
import com.ios.model.Employee;
import com.ios.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Api(value = "Employee Management", protocols = "http")
@Slf4j
public class EmployeeConrtoller {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/get-all-employee")
	@ApiOperation(value = "To Get All Employee List", response = Employee.class, code = 200)
	public List<Employee> getAllEmployee() {
		log.info("get all employee call...");
		return employeeService.getAllEmployee();
	}

	@PostMapping("/save-employee")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "To Create New Employee By Passing Employee Info", response = Employee.class, code = 201)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@DeleteMapping("/delete-by-id/{employeeId}")
	@ApiOperation(value = "To Delete Employee By Passing Employee Id", response = Employee.class, code = 204)
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("employeeId") long employeeId)
			throws EmployeeNotFoundExceptoin {
		boolean flag=employeeService.deleteEmployeeById(employeeId);
		if(flag)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update-employee-info/{employeeId}")
	@ApiOperation(value = "To Update Employee By Passing Employee Id, Name, Salry,Phone Number", response = Employee.class, code = 200)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") long employeeId,
			@RequestBody Employee employee) throws EmployeeNotFoundExceptoin {
		return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employee),HttpStatus.OK);
	}

	@GetMapping("/find-by-name/{name}")
	@ApiOperation(value = "To Find Employee By Passing Employee Name", response = Employee.class, code = 200)
	public List<Employee> findByName(@PathVariable("name") String name) throws EmployeeNotFoundExceptoin, WrongInputException {
		return employeeService.findByName(name);
	}
	
	@GetMapping("/find-by-id/{employeeId}")
	@ApiOperation(value = "To Find Employee By Passing Employee Id", response = Employee.class, code = 200)
	public Employee findById(@PathVariable("employeeId") long employeeId) throws EmployeeNotFoundExceptoin {
		return employeeService.findById(employeeId);
	}
	
}
