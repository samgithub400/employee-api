package com.ios.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ios.exception.EmployeeNotFoundExceptoin;
import com.ios.exception.WrongInputException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;
import com.ios.service.EmployeeService;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.getAllEmployee();
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.saveEmployee(employee);
	}

	@Override
	public boolean deleteEmployeeById(long employeeId) throws EmployeeNotFoundExceptoin {

		return employeeRepository.deleteEmployeeById(employeeId);
	}

	@Override
	public Employee updateEmployee(long employeeId, Employee employee) throws EmployeeNotFoundExceptoin {

		return employeeRepository.updateEmployee(employeeId, employee);
	}

	@Override
	public List<Employee> findByName(String name) throws EmployeeNotFoundExceptoin, WrongInputException {

		return employeeRepository.findByName(name);
	}

	@Override
	public Employee findById(long employeeId) throws EmployeeNotFoundExceptoin {
		
		return employeeRepository.findById(employeeId);
	}
}
