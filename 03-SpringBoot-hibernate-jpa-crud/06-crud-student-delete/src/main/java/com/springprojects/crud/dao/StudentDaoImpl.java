package com.springprojects.crud.dao;

import com.springprojects.crud.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
