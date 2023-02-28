package com.spring.app.dao;


import com.spring.app.entity.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO add exception handling code manage various scenarios
@Repository
public class StudentDaoMySqlImpl implements StudentDao {
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public void delete(Student student) {
        hibernateTemplate.delete(student);
    }

    @Override
    public Student getStudentById(int id) {
        Student student = hibernateTemplate.get(Student.class, id);
        return student;
    }

    @Override
    public void updateStudentDetail(Student student) {
        hibernateTemplate.update(student);
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public StudentDaoMySqlImpl() {
    }

    public StudentDaoMySqlImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    @Transactional //by default readOnly = false
    public int insert(Student student) {
        return (Integer) hibernateTemplate.save(student);
    }

    @Override
    @Transactional
    public List<Student> getAllRecords() {
        //TODO catch exception
        List<Student> students = hibernateTemplate.loadAll(Student.class);
        return students;
    }
}
