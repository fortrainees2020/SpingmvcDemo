package com.cts.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cts.demo.exception.EmployeeNotFoundException;
import com.cts.demo.model.Employee;
import com.cts.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/empapi")

public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;
	


	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public String getAllEmployees(Model model)
	{
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employeesListDisplay";
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
	 int empId = Integer.parseInt(request.getParameter("id"));
	 
	  employeeService.deleteEmployee(empId);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newEmployee(ModelAndView model) {
	    Employee newEmp= new Employee();
	    model.addObject("employee", newEmp);
	    model.setViewName("EmployeeForm");
	    return model;
	}
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	 
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
	if(employee.getName().isEmpty())
			throw new RuntimeException();
		  employeeService.saveOrUpdate(employee); 
		  return new  ModelAndView("redirect:/empapi/getAllEmployees");
	}
	
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request)  {
			int empId = Integer.parseInt(request.getParameter("id"));
	    System.out.println(" Employee Id from  edit form: "+empId);
	    Employee employee = employeeService.getId(empId);
	    System.out.println("Employee Object :"+employee);
	    ModelAndView model = new ModelAndView("EmployeeForm");
	    
	    model.addObject("employee", employee);	 
	    return model;
	    
	}
	
	@ExceptionHandler(value=RuntimeException.class)
	public String exceptionHandler()
	{	 return "RuntimeException";
		
	}
	
	
}