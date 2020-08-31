package com.begar.demo.repository;

import com.begar.demo.entity.Student;
import com.begar.demo.entity.StudentResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentResultRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentResult> getStudentResults() {
        List results = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            results = session.createQuery("from StudentResult ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;
    }

    public StudentResult getStudentResult(int id) {
        StudentResult result = new StudentResult();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = session.get(StudentResult.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public void addStudentResult(StudentResult studentResult, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = studentRepository.getStudent(id);
            studentResult.setStudent(student);
            session.save(studentResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateStudentResult(StudentResult studentResult, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(studentResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudentResult(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            StudentResult studentResult = session.get(StudentResult.class, id);
            if (studentResult != null) {
                session.delete(studentResult);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
