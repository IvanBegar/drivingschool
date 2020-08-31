package com.begar.demo.repository;

import com.begar.demo.entity.User;
import com.begar.demo.exception.DataException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

@Repository
public class UserRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public void addNewUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "insert into users (role_id, username, password, phone) values (?, ?, ?, ?);";
            Query query = session.createSQLQuery(sql)
                    .setParameter(1, user.getRole().getRole_id())
                    .setParameter(2, user.getUsername())
                    .setParameter(3, user.getPassword())
                    .setParameter(4, user.getPhone());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUserByName(String username) throws DataException {
        User user = new User();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User as u where u.username = :username");
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
}
