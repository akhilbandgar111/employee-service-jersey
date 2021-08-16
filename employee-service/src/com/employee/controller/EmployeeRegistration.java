package com.employee.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.employee.beans.Employee;
import com.employee.service.EmployeeService;
import com.employee.util.HttpStatus;

@Path("/")
public class EmployeeRegistration
{
	private EmployeeService empservice = new EmployeeService();

	@Path("/add")
	@POST
	public String registerEmployee(Employee employee) throws Exception
	{
		employee.setFname(employee.getFname());
		employee.setMiddleName(employee.getMiddleName());
		employee.setLastName(employee.getLastName());
		employee.setAge(employee.getAge());
		employee.setGender(employee.getGender());
		employee.setContactNumber(employee.getContactNumber());
		employee.setModified_by(employee.getModified_by());
		employee.setCreated_by(employee.getCreated_by());
		empservice.registerEmployee(employee);

		return HttpStatus.SUCCESS.name();
	}

	@Path("/fetch")
	@GET
	public List<Employee> getEmployeelist() throws Exception
	{
		return empservice.getEmployeelist();
	}

	@Path("/update")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public String updateEmployee(Employee employee) throws Exception
	{
		empservice.updateEmployee(employee);
		return HttpStatus.SUCCESS.name();
	}

	@Path("/delete")
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	public String deleteEmployee(@QueryParam("id") int empId) throws Exception
	{
		empservice.deleteEmployee(empId);
		return HttpStatus.SUCCESS.name();
	}

}
