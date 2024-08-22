package com.springprojects.Springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.springprojects.common", "com.springprojects.Springcore"})
public class SpringcoreApplication {

    /**
     * 1. Default Behavior: If scanBasePackages is not specified, @SpringBootApplication scans the package where the main application class is located and all its sub-packages.
     * 2. Specifying Packages: If you use scanBasePackages with specific packages, Spring Boot will only scan those packages and their sub-packages.
     * 3. Including Multiple Packages: To include multiple packages, either list them in scanBasePackages or remove scanBasePackages to use the default scanning behavior.
     **/
    public static void main(String[] args) {
        SpringApplication.run(SpringcoreApplication.class, args);
    }

}
