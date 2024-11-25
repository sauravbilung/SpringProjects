package com.springprojects.restcrud.controller;

import com.springprojects.restcrud.entity.Student;
import com.springprojects.restcrud.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
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
}
