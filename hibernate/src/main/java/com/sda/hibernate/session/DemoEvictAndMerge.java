package com.sda.hibernate.session;

import com.sda.hibernate.config.HibernateUtil;
import com.sda.hibernate.states.Person;
import com.sda.hibernate.states.PersonDao;
import org.hibernate.Session;

// sometime we need to detach the session object because we need to change it
// and it may take some time. it's better to detach object from hibernate session
public class DemoEvictAndMerge {

    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();

        Person person = new Person();
        person.setFirstName("jon");
        person.setLastName("snow");
        person.setEmail("jonsnow@gmail.com");
        person.setCountry("westeros");
        // transient

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        personDao.create(person);
        // persistent

        // find person by id
        Person foundPerson = session.find(Person.class, 1L);
        System.out.println("persisted - first name = " + foundPerson.getFirstName());

        // evict the object
        session.evict(foundPerson);

        // update
        foundPerson.setFirstName("evicted");

        // flushing is the process of synchronizing the underlying persistent
        // store with persistable state held in memory.
        // Must be called at the end of a unit of work, before committing the
        // transaction and closing the session
        session.flush();

        foundPerson = session.find(Person.class, 1L);
        System.out.println("after evict - first name = " + foundPerson.getFirstName());

        // merge object

        session.merge(foundPerson);

        // update
        foundPerson.setFirstName("merged");

        session.flush();

        foundPerson = session.find(Person.class, 1L);
        System.out.println("after merge - first name = " + foundPerson.getFirstName());

        session.close();
    }
}
