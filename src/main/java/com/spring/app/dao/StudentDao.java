package com.spring.app.dao;


import com.spring.app.entity.Student;

import java.util.List;

public interface StudentDao {
    int insert(Student student);

    void delete(Student student);

    Student getStudentById(int id);

    void updateStudentDetail(Student student);

    List<Student> getAllRecords();
}
