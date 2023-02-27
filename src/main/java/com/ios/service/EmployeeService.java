package com.ios.service;

import java.util.List;

import com.ios.exception.EmployeeNotFoundExceptoin;
import com.ios.exception.WrongInputException;
import com.ios.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();

	public Employee saveEmployee(Employee employee);

	public boolean deleteEmployeeById(long employeeId) throws EmployeeNotFoundExceptoin;

	public Employee updateEmployee(long employeeId, Employee employee) throws EmployeeNotFoundExceptoin;

	public List<Employee> findByName(String name) throws EmployeeNotFoundExceptoin, WrongInputException;

	public Employee findById(long employeeId) throws EmployeeNotFoundExceptoin;

}
