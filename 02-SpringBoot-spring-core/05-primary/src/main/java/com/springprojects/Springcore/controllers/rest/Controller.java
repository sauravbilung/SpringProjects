package com.springprojects.Springcore.controllers.rest;

import com.springprojects.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Coach myCoach;

    @Autowired
    public Controller(Coach myCoach) {
        // If Qualifier and Primary both are used then Qualifier is given priority.
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        // Spring will automatically use this constructor for dependency injection, even if we don’t explicitly annotate it with @Autowired.
        // If there are multiple constructors, we will need to use @Autowired to specify which constructor should be used for dependency injection.
        return myCoach.getDailyWorkout();
    }
}
