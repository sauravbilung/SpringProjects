package com.springprojects.crud.dao;

import com.springprojects.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository does the following :
 * 1. The class becomes eligible for component scanning.
 * 2. Translate JDBC exceptions.
 * 3. It gives the msg that the class is specifically designed for database operations,
 * helping to improve the readability and maintainability of the codebase.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private static final String LAST_NAME = "lastname";
    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        /**
         * Spring Boot looks for the spring.datasource.* properties in the configuration file.
         * It then sets up a DataSource bean, an EntityManagerFactory bean, and eventually an EntityManager bean.
         * The EntityManager is injected into the StudentDaoImpl class because Spring detects it as a bean and injects it automatically.
         * Spring Boot identifies the driver based on the datasource url.
         * If we need to manually define the driver we can use this property : spring.datasource.driver-class-name
         * */
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        // "From Student" : Student is Jpa entity name(class name) and not the table name.
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:"+LAST_NAME, Student.class);
        query.setParameter(LAST_NAME, lastName);
        return query.getResultList();
    }
}
