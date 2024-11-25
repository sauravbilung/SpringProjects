package com.springprojects.crud;

import com.springprojects.crud.dao.StudentDao;
import com.springprojects.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            System.out.println("Fetching all students...");
            queryForStudents(studentDao);
            System.out.println("Fetching students by last name...");
            queryForStudentsByLastName(studentDao);
        };
    }

    private void queryForStudents(StudentDao studentDao){
        List<Student> students = studentDao.findAll();

        for(Student student: students){
            System.out.println(student);
        }
    }

    private void queryForStudentsByLastName(StudentDao studentDAO) {

        List<Student> students = studentDAO.findByLastName("Wayne");

        for (Student student : students) {
            System.out.println(student);
        }
    }

}




