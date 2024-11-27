package com.springprojects.restcrud.dao;

import com.springprojects.restcrud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
