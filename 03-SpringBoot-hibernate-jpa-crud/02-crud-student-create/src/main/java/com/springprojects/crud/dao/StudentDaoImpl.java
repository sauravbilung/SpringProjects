package com.springprojects.crud.dao;

import com.springprojects.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Repository does the following :
 * 1. The class becomes eligible for component scanning.
 * 2. Translate JDBC exceptions.
 * 3. It gives the msg that the class is specifically designed for database operations,
 * helping to improve the readability and maintainability of the codebase.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

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
    @Transactional
    public void save(Student theStudent) {
        /**
         * The @Transactional annotation defines the transactional boundary. All the operations performed inside the method from the start
         * to the end are treated as a single unit of work. If any part of this work fails, none of the operations will be applied to the database.
         * */
        entityManager.persist(theStudent);
    }
}
