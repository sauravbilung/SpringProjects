package com.springprojects.restcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    /*
    @ControllerAdvice is a specialized Spring annotation used to define global exception handling logic across multiple
    controllers in a Spring application. It centralizes and simplifies error handling, making it easier to manage consistent error responses for exceptions.
    It acts as an interceptor for exceptions thrown in controller methods annotated with @RequestMapping or other HTTP mapping annotations (@GetMapping, @PostMapping, etc.).
    It decouples exception-handling logic from individual controller classes and provides a centralized location for managing exceptions.
    */

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
