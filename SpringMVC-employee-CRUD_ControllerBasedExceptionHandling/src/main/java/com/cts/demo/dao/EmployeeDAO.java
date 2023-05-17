package com.cts.demo.dao;

import java.util.List;

import com.cts.demo.model.Employee;

public interface EmployeeDAO 
{
	public List<Employee> getAllEmployees();
	public void deleteEmployee(int Id);
	 public void saveOrUpdate(Employee employee);
	 public Employee get(int empId) ;
		
}