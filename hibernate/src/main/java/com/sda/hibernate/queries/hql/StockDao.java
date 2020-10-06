package com.sda.hibernate.queries.hql;


import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StockDao {

    // TODO: use a logging framework log4j2 / slf4j
    // replace default java logger with a logging framework
    private static final Logger logger = java.util.logging.Logger.getLogger(StockDao.class.getName());

    public static void main(String[] args) {
        logger.info("info message");
        logger.warning("warn message");
        logger.severe("error message");
    }

    // create
    public void create(Stock stock) {
        logger.info("saving stock: " + stock);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(stock);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // find all
    public List<Stock> findAllWithHqlQuery() {
        List<Stock> result = new ArrayList<>();
        // query using HQL
        String hql = "FROM com.sda.hibernate.queries.hql.Stock";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Stock> query = session.createQuery(hql, Stock.class);
            result = query.getResultList();
        } catch (Exception e) {
            logger.severe("error executing hql:" + hql);
        }
        return result;
    }

    public List<Stock> findAllByStockCodeWithNamedQuery(String stockCode) {
        List<Stock> result = new ArrayList<>();

        String hql = "findStockByStockCode";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // named query with parameters using HQL
            Query<Stock> query = session.createNamedQuery(hql, Stock.class);
            query.setParameter("stockCode", stockCode);

            result = query.getResultList();
        } catch (Exception e) {
            logger.severe("error executing hql:" + hql);
        }
        return result;
    }
}
