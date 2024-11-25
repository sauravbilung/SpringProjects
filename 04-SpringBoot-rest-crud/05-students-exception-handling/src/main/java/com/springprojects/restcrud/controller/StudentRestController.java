package com.springprojects.restcrud.controller;

import com.springprojects.restcrud.entity.Student;
import com.springprojects.restcrud.exception.StudentErrorResponse;
import com.springprojects.restcrud.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();
        theStudents.add(new Student("Bruce", "Wayne"));
        theStudents.add(new Student("Clark", "Kent"));
        theStudents.add(new Student("Tony", "Stark"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }


    /*
    The final response sent to the client is always a ResponseEntity object, regardless of whether we explicitly return it in the code or let Spring handle it implicitly.
    When we return an object like Student, Spring automatically wraps it in an HTTP response with the following default behavior:
    HTTP Status Code: Defaults to 200 OK.
    Headers: Uses default headers.
    Body: Contains the serialized Student object (converted to JSON/XML).
    */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // Indexing into the list. Keeping it simple for now
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    /*
    ResponseEntity is useful when we need flexibility in the response. With it, we can:

    Return different HTTP status codes (404, 400, 500, etc.).
    Customize the response format (e.g., error details, metadata).
    Handle different scenarios (e.g., success, failure) in the same method.

    When returning Student directly, Spring assumes the response is successful (200 OK) and automatically serializes the object.
    When an exception is thrown, Spring uses the @ExceptionHandler method, which wraps the error response (like StudentErrorResponse)
    in a ResponseEntity with the specified status code.
    */

    // This method is called whenever a StudentNotFoundException is thrown in the controller.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // This method is called for any exception that is not specifically handled elsewhere.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
