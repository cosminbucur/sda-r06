package com.sda.hibernate.associations.one_to_one;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDao {

    public void create(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
