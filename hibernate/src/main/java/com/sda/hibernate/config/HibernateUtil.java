package com.sda.hibernate.config;

import com.sda.hibernate.queries.hql.Stock;
import com.sda.hibernate.queries.hql_join.Author;
import com.sda.hibernate.queries.hql_join.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // get a session factory
    private static SessionFactory buildSessionFactory() {
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
        settings.put(Environment.HBM2DDL_AUTO, "create");

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

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);

        // native queries
//        configuration.addAnnotatedClass(Trader.class);

        // entity states
//        configuration.addAnnotatedClass(Person.class);

        // audit
//        configuration.addAnnotatedClass(Customer.class);
//        configuration.addAnnotatedClass(CustomerHistory.class);

        return configuration;
    }
}
