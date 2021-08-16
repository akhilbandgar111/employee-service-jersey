package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.employee.beans.Employee;
import com.employee.util.DBConnection;
import com.employee.util.EmployeeConstants;

public class EmployeeDao
{
	public void registerEmployee(Employee employee) throws Exception
	{
		try
		{
			Connection con = DBConnection.getConnection();
			String sql = "INSERT INTO employee_details(first_name,middle_name,last_name,age,gender,contect_number,created_by,created_on,modified_by,modified_on,is_deleted"
					+ ") values(?,?,?,?,?,?,?,GETDATE(),?,GETDATE(),?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, employee.getFname());
			pst.setString(2, employee.getMiddleName());
			pst.setString(3, employee.getLastName());
			pst.setInt(4, employee.getAge());
			pst.setString(5, employee.getGender());
			pst.setLong(6, employee.getContactNumber());
			pst.setString(7, employee.getCreated_by());
			pst.setString(8, employee.getModified_by());
			pst.setInt(9, 0);
			pst.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public List<Employee> getEmployeelist() throws Exception
	{
		try
		{
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM employee_details WHERE is_deleted=0";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<Employee> list = new ArrayList<Employee>();
			while (rs.next())
			{
				Employee employee = new Employee();
				employee.setEmpId(rs.getInt(EmployeeConstants.ID));
				employee.setFname(rs.getString(EmployeeConstants.EMPLOYEE_FIRST_NAME));
				employee.setMiddleName(rs.getString(EmployeeConstants.EMPLOYEE_MIDDLE_NAME));
				employee.setLastName(rs.getString(EmployeeConstants.EMPLOYEE_LAST_NAME));
				employee.setAge(rs.getInt(EmployeeConstants.AGE));
				employee.setGender(rs.getString(EmployeeConstants.GENDER));
				employee.setContactNumber(rs.getLong(EmployeeConstants.CONTACT_NO));
				employee.setCreated_by(rs.getString(EmployeeConstants.CREATED_BY));
				employee.setCreated_on(rs.getDate(EmployeeConstants.CREATED_ON));
				employee.setModified_by(rs.getString(EmployeeConstants.MODIFIED_BY));
				employee.setModified_on(rs.getDate(EmployeeConstants.MODIFIED_ON));
				list.add(employee);
			}
			return list;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void updateEmployee(Employee employee) throws Exception
	{
		try
		{
			Connection con = DBConnection.getConnection();
			String sql = "UPDATE employee_details SET first_name=?,middle_name=?,last_name=?,age=?,gender=?,contect_number=?,modified_by=?,modified_on=GETDATE() WHERE id=? and is_deleted=0";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, employee.getFname());
			pst.setString(2, employee.getMiddleName());
			pst.setString(3, employee.getLastName());
			pst.setInt(4, employee.getAge());
			pst.setString(5, employee.getGender());
			pst.setLong(6, employee.getContactNumber());
			pst.setString(7, employee.getModified_by());
			pst.setInt(8, employee.getEmpId());

			pst.executeUpdate();

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void deleteEmployee(@QueryParam("id") int empId) throws Exception
	{
		try
		{
			Connection con = DBConnection.getConnection();
			String sql = "UPDATE employee_details SET is_deleted=? WHERE id=? and is_deleted = 0";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, empId);
			pst.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
