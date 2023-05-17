package com.cts.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.cts.demo.model.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
@Autowired
	JdbcTemplate  template;
public EmployeeDaoImpl (DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
}  
   
	public EmployeeDaoImpl() {	super();  }

	public List<Employee> getAllEmployees() {
		 return template.query("select * from employee",new RowMapper<Employee>(){    
		        public Employee mapRow(ResultSet rs, int row) throws SQLException {    
		            Employee e=new Employee();    
		            e.setId(rs.getInt(1));    
		            e.setName(rs.getString(2));    
		            e.setSalary(rs.getFloat(3));    
		            e.setDesignation(rs.getString(4));    
		            return e;    
		        }    
		    });  
	}

	public void deleteEmployee(int Id) {
	    String sql = "DELETE FROM employee WHERE id=?";
		    template.update(sql, Id);
		}

	public void saveOrUpdate(Employee employee) {
		System.out.println(" In saveOrUpdate in dao implementation");
		if (employee.getId() > 0) {
	        // update
	        String sql = "UPDATE employee SET name=?, salary=?, designation=? WHERE id=?";
	        template.update(sql, employee.getName(), employee.getSalary(),
	              employee.getDesignation(),employee.getId());
	    } else {
	        // insert
	    	System.out.println(" In saveOrUpdate in dao implementation---------Insert Employee");
	        String sql = "insert into employee values(?,?,?,?)";
	        template.update(sql, employee.getId(),employee.getName(), employee.getSalary(),employee.getDesignation());
	    }
	 
		
	}

	public Employee get(int empId) {
		System.out.println(" Get called :"+empId);
		String sql = "SELECT * FROM employee WHERE id=" + empId;
	    return template.query(sql, new ResultSetExtractor<Employee>() {
	 
	        public Employee extractData(ResultSet rs) throws SQLException,  DataAccessException {
	            if (rs.next()) {
	                Employee employee  = new Employee();
	                employee.setId(rs.getInt("id"));
	                employee.setName(rs.getString("name"));
	                employee.setSalary(rs.getFloat("salary"));
	                employee.setDesignation(rs.getString("designation"));
	                return employee;
	            }	 
	            return null;
	        }
	 
	    });
	}
}    
	