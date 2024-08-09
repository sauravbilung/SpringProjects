package com.springprojects.SpringApp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // inject properties
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        String resultString = "Team %s is coached by %s";
        String result = String.format(resultString, teamName, coachName);
        return result;
    }

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
