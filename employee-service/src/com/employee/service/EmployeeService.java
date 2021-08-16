package com.employee.service;

import java.util.List;

import javax.ws.rs.QueryParam;

import com.employee.beans.Employee;
import com.employee.dao.EmployeeDao;

public class EmployeeService
{
	EmployeeDao employeedao = new EmployeeDao();

	public void registerEmployee(Employee employee) throws Exception
	{
		employeedao.registerEmployee(employee);
	}

	public List<Employee> getEmployeelist() throws Exception
	{
		return employeedao.getEmployeelist();
	}

	public void updateEmployee(Employee employee) throws Exception
	{
		employeedao.updateEmployee(employee);
	}

	public void deleteEmployee(@QueryParam("id") int empId) throws Exception
	{
		employeedao.deleteEmployee(empId);
	}

}
