package com.springprojects.restcrud.controller;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestCrudController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
