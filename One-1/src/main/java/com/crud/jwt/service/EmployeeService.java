package com.crud.jwt.service;

import java.util.List;

import com.crud.jwt.model.Employee;

public interface EmployeeService {
	public abstract Employee createEmployee(Employee employee);
	public abstract void updateEmployee(Employee employee);
	public abstract Employee getEmployee(int id);
	public abstract List<Employee> getEmployees();
	public abstract void deleteEmployee(int id);
	public abstract boolean isEmployee(int id);

	

}