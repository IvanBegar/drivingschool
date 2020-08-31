package com.begar.demo.repository;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Schedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Schedule> getSchedules() {
        List schedules = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            schedules = session.createQuery("from Schedule ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return schedules;
    }

    public Schedule getSchedule(int id) {
        Schedule schedule = new Schedule();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            schedule = session.get(Schedule.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return schedule;
    }

    public void addSchedule(Schedule schedule) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(schedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateSchedule(Schedule schedule) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(schedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteSchedule(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Schedule schedulet = session.get(Schedule.class, id);
            if (schedulet != null) {
                session.delete(schedulet);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        List schedules = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            schedules = session.createSQLQuery("SELECT study_group.groupName as groupName, " +
                    "schedule.name as scheduleName, " +
                    "schedule.description as description " +
                    "FROM schedule\n" +
                    "inner join study_group on schedule.schedule_id=study_group.schedule_id;")
                    .setResultTransformer(Transformers.aliasToBean(SchedulesPerGroupsDTO.class))
                    .list();
        }  catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return schedules;
        }
}
