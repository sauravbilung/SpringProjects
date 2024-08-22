package com.springprojects.Springcore.controllers.rest;

import com.springprojects.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public Controller(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("cricketCoach") Coach anotherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = myCoach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        // false for @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        // true for @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
        boolean areObjectsEqual = myCoach == anotherCoach;
        String result = String.format("Comparing beans: myCoach == anotherCoach, " + areObjectsEqual);
        return result;
    }
}
