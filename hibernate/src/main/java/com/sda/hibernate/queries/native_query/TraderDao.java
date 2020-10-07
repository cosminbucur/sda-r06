package com.sda.hibernate.queries.native_query;


import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TraderDao {

    private static final Logger logger = Logger.getLogger(TraderDao.class.getName());

    public void create(Trader trader) {
        logger.info("saving trader: " + trader);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(trader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // find all
    public List<Trader> findAllWithNativeQuery() {
        List<Trader> result = new ArrayList<>();
        // query using native SQL
        String sql = "SELECT * FROM trader";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Trader> query = session.createNativeQuery(sql, Trader.class);
            result = query.list();
        } catch (Exception e) {
            logger.severe("error executing sql:" + sql);
        }
        return result;
    }

    public List<Trader> findAllByNameWithNamedNativeQuery(String name) {
        List<Trader> result = new ArrayList<>();

        String nativeQueryName = "findTradersByNameNativeSQL";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // named query with parameters using HQL
            Query<Trader> namedNativeQuery = session.createNamedQuery(nativeQueryName, Trader.class);
            namedNativeQuery.setParameter("name", name);

            result = namedNativeQuery.getResultList();
        } catch (Exception e) {
            logger.severe("error executing nativeQueryName:" + nativeQueryName);
        }
        return result;
    }
}
