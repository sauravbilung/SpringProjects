package com.springprojects.SpringApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // These new end points are added without stopping the application.
    // For Intellij some more configurations are needed to be done for auto make and deploy.
    @GetMapping("/workout")
    public String doWorkout() {
        return "Did you go to gym today ?";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
