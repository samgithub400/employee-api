package com.ios.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.ios.exception.EmployeeNotFoundExceptoin;
import com.ios.exception.WrongInputException;
import com.ios.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeeRepository {

	private List<Employee> employeeData = new ArrayList<>();

	@PostConstruct
	private void employeeData() {

		employeeData.add(new Employee(1, "Akash", 12000, 899400400));
		employeeData.add(new Employee(15, "Akant", 13000, 889400400));
		employeeData.add(new Employee(19, "Akas", 16000, 799400499));
		employeeData.add(new Employee(12, "Ajay", 20000, 909789400));
		employeeData.add(new Employee(13, "Aman", 10000, 899123456));
		employeeData.add(new Employee(11, "John", 19000, 899400401));

	}

	public List<Employee> getAllEmployee() {
		return employeeData;
	}

	public Employee saveEmployee(Employee employee) {
		if (employeeData.add(employee))
			return employee;
		else
			return null;
	}

	public boolean deleteEmployeeById(long employeeId) throws EmployeeNotFoundExceptoin {

		log.info("Searching....");
		Employee foundEmployee = findById(employeeId);
		log.info("Found Employee....{}",foundEmployee);			
			return employeeData.remove(foundEmployee);	
	}

	public Employee updateEmployee(long employeeId, Employee updatedEmployee)
			throws EmployeeNotFoundExceptoin {
		
			Employee foundEmployee =findById(employeeId);
			
			foundEmployee.setName(updatedEmployee.getName());
			foundEmployee.setPnoheNumber(updatedEmployee.getPnoheNumber());
			foundEmployee.setSalary(updatedEmployee.getSalary());

			int index = employeeData.indexOf(foundEmployee);
			employeeData.set(index, foundEmployee);
			return 	foundEmployee;	
	}

	public List<Employee> findByName(String name) throws EmployeeNotFoundExceptoin, WrongInputException {

		StringBuilder bld = new StringBuilder();

		if (name.length() >= 3) {
			for (int i = 0; i <= 2; ++i) {
				bld.append(name.charAt(i));
			}
		} else {
			log.info("INPUT INVALID :{}", name);
			throw new WrongInputException("INPUT INVALID...");
		}
		String str = bld.toString();

		List<Employee> employeeList = employeeData.stream().filter(emp -> emp.getName().startsWith(str))
				.collect(Collectors.toList());

		if (employeeList.isEmpty()) {
			log.info("Employee Not Found with name: {}", name);
			throw new EmployeeNotFoundExceptoin("NOT FOUND...!");
		}
		return employeeList;
	}

	public Employee findById(long employeeId) throws EmployeeNotFoundExceptoin {
		Optional<Employee> emp = 
				Optional.of(employeeData.stream().filter(employee -> employee.getEmployeeId() == employeeId).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundExceptoin("NOT FOUND...!")));

		return emp.get();
	}

}
