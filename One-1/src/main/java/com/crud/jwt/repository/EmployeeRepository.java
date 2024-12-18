package com.crud.jwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.crud.jwt.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
