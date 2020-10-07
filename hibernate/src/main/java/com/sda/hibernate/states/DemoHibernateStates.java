package com.sda.hibernate.states;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DemoHibernateStates {

    public static void main(String[] args) {
        // transient state
        Person person = new Person();
        person.setFirstName("jon");
        person.setLastName("snow");
        person.setEmail("jonsnow@gmail.com");
        person.setCountry("westeros");

        // persistent object state
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);

        transaction.commit();

        // detached
        session.close();
    }
}
