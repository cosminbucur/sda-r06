package com.sda.hibernate.audit.history;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerHistoryRepository {

    public void create(CustomerHistory customerHistory) {
        Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();

            session.save(customerHistory);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

}
