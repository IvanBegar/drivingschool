package com.begar.demo.utility;

import java.util.Properties;
import com.begar.demo.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useUnicode=true&serverTimezone=UTC&useSSL=true");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Document.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Schedule.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(StudentResult.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(UtilityPayment.class);
        configuration.addAnnotatedClass(Vehicle.class);
        configuration.addAnnotatedClass(VehiclePayment.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);

        configuration.setProperties(settings);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
