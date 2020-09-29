package com.sda.hibernate.types;

import com.sda.hibernate.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDao {

    // create
    public Player create(Player player) {
        Session session = null;
        Transaction transaction = null;
        Player result = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            // generate insert into player table
            session.save(player);

            transaction.commit();

            // find by username and return
            result = findByUsername(player.getUsername());
        } catch (HibernateException e) {
            // if one operation fails, then rollback
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    // read
    // find by username
    public Player findByUsername(String username) {
        Player result = null;

        try (Session session = getSession()) {
            // no transaction needed for read operations
            // HQL hibernate query language
            String findByUsernameHql = "FROM Player p WHERE p.username = :username";
            Query<Player> query = session.createQuery(findByUsernameHql);
            query.setParameter("username", username);

            List<Player> foundPlayers = query.getResultList();

            if (foundPlayers.isEmpty()) {
                return result;
            } else {
                result = foundPlayers.get(0);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // update
    // change the username of a player
    public Player update(Long id, Player playerDetails) {
        Player result = null;
        Transaction transaction = null;
        try (Session session = getSession()) {
            Player playerToBeUpdated = session.find(Player.class, id);

            transaction = session.beginTransaction();

            playerToBeUpdated.setUsername(playerDetails.getUsername());
            playerToBeUpdated.setAge(playerDetails.getAge());
            playerToBeUpdated.setStatus(playerDetails.getStatus());

            session.update(playerToBeUpdated);

            transaction.commit();
            result = session.find(Player.class, id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return result;
    }

    // delete
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            Player playerToBeDeleted = session.find(Player.class, id);

            transaction = session.beginTransaction();

            session.delete(playerToBeDeleted);

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
