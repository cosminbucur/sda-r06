package com.sda.hibernate.config;

import com.sda.hibernate.queries.hql.Stock;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    // get a session factory
    public static SessionFactory getSessionFactory() {
        Configuration configuration = createConfig();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Configuration createConfig() {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(settings);

        // add annotated classes

        // hibernate types
//        configuration.addAnnotatedClass(Player.class);

        // one to one
//        configuration.addAnnotatedClass(Account.class);
//        configuration.addAnnotatedClass(Employee.class);

        // one to many unidirectional
//        configuration.addAnnotatedClass(Mother.class);
//        configuration.addAnnotatedClass(Daughter.class);

        // one to many unidirectional join
//        configuration.addAnnotatedClass(Father.class);
//        configuration.addAnnotatedClass(Son.class);

        // one to many bidirectional
//        configuration.addAnnotatedClass(Child.class);
//        configuration.addAnnotatedClass(Parent.class);
//
        // many to many bidirectional
//        configuration.addAnnotatedClass(Post.class);
//        configuration.addAnnotatedClass(Tag.class);

        // HQL queries
        configuration.addAnnotatedClass(Stock.class);

        return configuration;
    }
}
