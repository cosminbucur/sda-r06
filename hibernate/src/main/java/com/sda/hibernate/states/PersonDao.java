package com.sda.hibernate.states;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonDao {

    public void create(Person person) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();

            session.save(person);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Person findByUsername(String username) {
        Person result = null;

        try (Session session = getSession()) {
            String findByUsernameHql = "FROM Person p WHERE p.firstName = :firstName";
            Query<Person> query = session.createQuery(findByUsernameHql);
            query.setParameter("firstName", username);

            List<Person> foundPersons = query.getResultList();

            if (foundPersons.isEmpty()) {
                return result;
            } else {
                result = foundPersons.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Person update(Long id, Person personDetails) {
        Person result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Person personToBeUpdated = session.find(Person.class, id);

            transaction = session.beginTransaction();

            personToBeUpdated.setFirstName(personDetails.getFirstName());
            personToBeUpdated.setLastName(personDetails.getLastName());
            personToBeUpdated.setEmail(personDetails.getEmail());
            personToBeUpdated.setCountry(personDetails.getCountry());

            session.update(personToBeUpdated);

            transaction.commit();
            result = session.find(Person.class, id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Person personToBeDeleted = session.find(Person.class, id);

            transaction = session.beginTransaction();

            session.delete(personToBeDeleted);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

}
