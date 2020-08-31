package com.begar.demo.repository;

import com.begar.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Group> getGroups() {
        List groups = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            groups = session.createQuery("from Group ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return groups;
    }

    public Group getGroup(int id) {
        Group group = new Group();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            group = session.get(Group.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return group;
    }

    public void addGroup(Group group, int category_id, int schedule_id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Category category = categoryRepository.getCategory(category_id);
            Schedule schedule = scheduleRepository.getSchedule(schedule_id);
            group.setCategory(category);
            group.setSchedule(schedule);
            session.save(group);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group, int category_id, int schedule_id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Category category = categoryRepository.getCategory(category_id);
            Schedule schedule = scheduleRepository.getSchedule(schedule_id);
            group.setCategory(category);
            group.setSchedule(schedule);
            session.update(group);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteGroup(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            if (group != null) {
                session.delete(group);
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
