package com.springprojects.restcrud.service;

import com.springprojects.restcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
