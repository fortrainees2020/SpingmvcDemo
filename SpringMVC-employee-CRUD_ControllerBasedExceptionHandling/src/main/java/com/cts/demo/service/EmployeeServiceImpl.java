package com.cts.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dao.EmployeeDAO;
import com.cts.demo.exception.EmployeeNotFoundException;
import com.cts.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO dao;
	
	public List<Employee> getAllEmployees() 
	{	List<Employee> emplist= dao.getAllEmployees();
		for(Employee e: emplist)
		{	System.out.println(e.getId() + e.getName() + e.getSalary());	}
		return emplist;
	}	
		
	public void deleteEmployee(int id) {
		System.out.println("Employee id in service "+ id);
		dao.deleteEmployee(id);	
	}

	public void saveOrUpdate(Employee employee) {
		System.out.println(" In saveOrUpdate");
		dao.saveOrUpdate(employee);
	}
	
	public Employee getId(int empId) {
		Employee employee=null;		
			 employee=dao.get(empId);		
		 return employee;
	}
	
}
