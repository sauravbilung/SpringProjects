package com.springprojects.crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    /**
     * The CommandLineRunner is an interface in Spring Boot. When a class implements this interface, Spring Boot will automatically run its run method
     * after loading the application context. Usually, we use this CommandLineRunner to perform startup tasks like user or database initialization, seeding,
     * or other startup activities.
     * In this case Spring will initialize the bean, recognize it as a CommandLineRunner, and trigger its run() method, which is where the logic gets executed.
     * The runner -> syntax is a lambda expression. It's shorthand for implementing the run method of the CommandLineRunner interface.
     */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner -> {
            System.out.println("Hello World");
        };
    }
}




