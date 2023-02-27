package com.ios.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ios.exception.EmployeeNotFoundExceptoin;
import com.ios.exception.WrongInputException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	void testSaveEmployee() {

		Employee employee = new Employee();
		employee.setEmployeeId(111);
		employee.setName("Manoj");
		employee.setPnoheNumber(123456789);
		employee.setSalary(12300);

		Mockito.when(employeeRepository.saveEmployee(employee)).thenReturn(employee);
		assertThat(employeeService.saveEmployee(employee)).isEqualTo(employee);

	}

	@Test
	void testGetAllEmployee() {
		List<Employee> employeeData = new ArrayList<>();
		employeeData.add(new Employee(1, "Akash", 12000, 899400400));
		employeeData.add(new Employee(15, "Akas", 13000, 889400400));
		employeeData.add(new Employee(19, "Arman", 16000, 799400499));

		Mockito.when(employeeRepository.getAllEmployee()).thenReturn(employeeData);
		assertThat(employeeService.getAllEmployee()).isEqualTo(employeeData);
	}

	@Test
	void testDeleteEmployeeById() throws EmployeeNotFoundExceptoin {

		Employee employee = new Employee();
		employee.setEmployeeId(111);
		employee.setName("Manoj");
		employee.setPnoheNumber(123456789);
		employee.setSalary(12300);

		Mockito.when(employeeRepository.findById(111)).thenReturn(employee);
		Mockito.when(employeeRepository.deleteEmployeeById(employee.getEmployeeId())).thenReturn(false);
		assertFalse(employeeRepository.deleteEmployeeById(employee.getEmployeeId()));
	}

	@Test
	void testUpdateEmployee() throws EmployeeNotFoundExceptoin {
		Employee employee = new Employee();
		employee.setEmployeeId(111);
		employee.setName("Manoj");
		employee.setPnoheNumber(123456789);
		employee.setSalary(12300);

		Mockito.when(employeeRepository.findById(111)).thenReturn(employee);

		employee.setName("Amol");
		employee.setPnoheNumber(999999999);

		Mockito.when(employeeRepository.updateEmployee(111, employee)).thenReturn(employee);

		assertThat(employeeService.updateEmployee(111, employee)).isEqualTo(employee);
	}

	@Test
	void testFindByName() throws EmployeeNotFoundExceptoin, WrongInputException {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1, "Akash", 12000, 899400400));
		employeeList.add(new Employee(15, "Aka", 13000, 889400400));
		employeeList.add(new Employee(19, "Akas", 16000, 799400499));

		Mockito.when(employeeRepository.findByName("Aka")).thenReturn(employeeList);
		assertThat(employeeService.findByName("Aka")).isEqualTo(employeeList);
	}
	
	@Test
	void testFindById() throws EmployeeNotFoundExceptoin {
		
		Employee employee = new Employee();
		
		employee.setEmployeeId(111);
		employee.setName("Manoj");
		employee.setPnoheNumber(123456789);
		employee.setSalary(12300);
		
		Mockito.when(employeeRepository.findById(111)).thenReturn(employee);
		assertThat(employeeService.findById(111)).isEqualTo(employee);

	}	
}
