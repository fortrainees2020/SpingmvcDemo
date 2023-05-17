package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.EmployeeNotFoundException;
import com.cts.demo.model.Employee;

public interface EmployeeService 
{	public void deleteEmployee(int id);
	public List<Employee> getAllEmployees();
	public void saveOrUpdate(Employee employee);
	public Employee getId(int empId)  ;
}
