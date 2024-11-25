package com.springprojects.crud.dao;

import com.springprojects.crud.entity.Student;

public interface StudentDao {
    Student findById(Integer id);
    void update(Student student);
}
