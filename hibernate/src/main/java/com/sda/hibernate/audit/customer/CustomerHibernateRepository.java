package com.sda.hibernate.audit.customer;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerHibernateRepository implements CustomerRepository {

    @Override
    public void create(Customer customer) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();

            session.save(customer);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Customer findById(Long id) {
        Customer result = null;
        try (Session session = getSession()) {
            result = session.find(Customer.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Customer update(Long id, Customer customerDetails) {
        Customer result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Customer customerToBeUpdated = session.find(Customer.class, id);

            transaction = session.beginTransaction();

            customerToBeUpdated.setFirstName(customerDetails.getFirstName());
            customerToBeUpdated.setLastName(customerDetails.getLastName());

            session.update(customerToBeUpdated);

            transaction.commit();
            result = session.find(Customer.class, id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Customer customerToBeDeleted = session.find(Customer.class, id);

            transaction = session.beginTransaction();

            session.delete(customerToBeDeleted);

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
