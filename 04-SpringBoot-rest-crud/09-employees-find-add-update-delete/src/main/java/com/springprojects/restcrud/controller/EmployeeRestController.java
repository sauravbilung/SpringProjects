package com.springprojects.restcrud.controller;

import com.springprojects.restcrud.entity.Employee;
import com.springprojects.restcrud.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // starting with Spring Framework 4.3, if a class has only one constructor, it is automatically eligible for dependency injection,
    // and we don't need to explicitly annotate the constructor with @Autowired.
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }


    /**
     * Handles HTTP POST requests to add a new employee.
     * <p>
     * This method accepts data in the HTTP request body, parses it into an {@code Employee} object,
     * and saves the employee to the database. The {@code @RequestBody} annotation is used to bind
     * the request body content to the {@code Employee} parameter.
     *
     * <p>How data parsing works:</p>
     * <ul>
     *   <li>By default, Spring Boot supports JSON as the input format using the
     *       {@code MappingJackson2HttpMessageConverter}. It maps JSON keys to the
     *       {@code Employee} object's fields by matching field names.</li>
     *   <li>For other formats (e.g., XML), additional configuration or dependencies may be required:
     *     <ul>
     *       <li>XML support typically requires adding JAXB dependencies and annotating the
     *           {@code Employee} class with JAXB annotations like {@code @XmlRootElement} and {@code @XmlElement}.</li>
     *       <li>Spring will then use the {@code Jaxb2RootElementHttpMessageConverter} to parse XML data.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param employee the {@code Employee} object parsed from the request body
     * @return the saved {@code Employee} object with its generated ID
     * @throws org.springframework.web.HttpMediaTypeNotSupportedException if the content type is unsupported
     */
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        /*
         * Why are we setting employee id as 0 in addEmployee ?
         *
         * 1. In the code, the @GeneratedValue(strategy = GenerationType.IDENTITY) annotation tells JPA to let the database generate the ID for the entity (e.g., using an auto-increment column).
         * 2. In such cases, JPA expects the application to provide a null or 0 ID for new entities.
         * 3. When the ID is 0 (or null), JPA interprets this as an indicator that the object is not yet persisted in the database.
         *
         */
        employee.setId(0);
        return employeeService.save(employee);
    }

    /**
     * Handles HTTP PUT requests to update an existing employee or add a new one if the employee does not exist.
     *
     * - If the {@code employee} object contains an {@code id} that matches an existing record in the database,
     *   JPA will update that record.
     * - If the {@code id} is not provided (defaults to 0 for primitive {@code int}) or does not exist in the database,
     *   JPA will insert a new record.
     *
     * Note: This behavior depends on the {@code EntityManager.merge()} method used in the service layer.
     */
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
