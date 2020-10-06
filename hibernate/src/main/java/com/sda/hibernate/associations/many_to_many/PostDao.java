package com.sda.hibernate.associations.many_to_many;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostDao {

    public void create(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Post findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Post post = session.find(Post.class, id);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
