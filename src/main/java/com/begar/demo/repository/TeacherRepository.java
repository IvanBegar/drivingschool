package com.begar.demo.repository;

import com.begar.demo.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Teacher> getTeachers() {
        List teachers = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            teachers = session.createQuery("from Teacher ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return teachers;
    }

    public Teacher getTeacher(int id) {
        Teacher teacher = new Teacher();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            teacher = session.get(Teacher.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return teacher;
    }

    public void addTeacher(Teacher teacher) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateTeacher(Teacher teacher) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTeacher(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher != null) {
                session.delete(teacher);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void addTeacherToGroup(int id1, int id2) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "insert into teacher_group (teacher_id, group_id) values (?, ?);";
            Query query = session.createSQLQuery(sql).setParameter(1, id1).setParameter(2, id2);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

