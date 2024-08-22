package com.springprojects.Springcore.controllers.rest;

import com.springprojects.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Coach myCoach;

    @Autowired
    public Controller(@Qualifier("footballCoach") Coach myCoach) {
        /* When we use @Component, @Service, @Repository, or other stereotype annotations without specifying a name,
        Spring automatically uses the lowercase version of the class name with the first letter in lowercase.
        So, FootballCoach would default to "footballCoach".
        */
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        // Spring will automatically use this constructor for dependency injection, even if we don’t explicitly annotate it with @Autowired.
        // If there are multiple constructors, we will need to use @Autowired to specify which constructor should be used for dependency injection.
        return myCoach.getDailyWorkout();
    }
}
