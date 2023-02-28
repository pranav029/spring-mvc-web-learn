package com.spring.app.services;

import com.spring.app.dao.StudentDao;
import com.spring.app.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    public List<Student> getAllStudent(){
        return studentDao.getAllRecords();
    }
    public int insertStudentRecord(Student student){
        return studentDao.insert(student);
    }
}
