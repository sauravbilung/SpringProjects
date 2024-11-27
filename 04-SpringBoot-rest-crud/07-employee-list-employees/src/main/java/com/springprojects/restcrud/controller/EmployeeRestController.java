package com.springprojects.restcrud.controller;

import com.springprojects.restcrud.dao.EmployeeDAO;
import com.springprojects.restcrud.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    // starting with Spring Framework 4.3, if a class has only one constructor, it is automatically eligible for dependency injection,
    // and we don't need to explicitly annotate the constructor with @Autowired.
    public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
