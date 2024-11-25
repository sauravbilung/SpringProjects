package com.springprojects.crud.dao;

import com.springprojects.crud.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
}
