package com.sda.hibernate.config;

import com.sda.hibernate.associations.one_to_many_bi.Child;
import com.sda.hibernate.associations.one_to_many_bi.Parent;
import com.sda.hibernate.associations.one_to_many_uni.Daughter;
import com.sda.hibernate.associations.one_to_many_uni.Mother;
import com.sda.hibernate.associations.one_to_many_uni_join.Father;
import com.sda.hibernate.associations.one_to_many_uni_join.Son;
import com.sda.hibernate.types.Player;
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
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(settings);

        // add annotated classes
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Mother.class);
        configuration.addAnnotatedClass(Daughter.class);
        configuration.addAnnotatedClass(Father.class);
        configuration.addAnnotatedClass(Son.class);
        configuration.addAnnotatedClass(Child.class);
        configuration.addAnnotatedClass(Parent.class);

        return configuration;
    }
}
