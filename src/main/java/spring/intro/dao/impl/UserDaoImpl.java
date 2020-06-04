package spring.intro.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import spring.intro.dao.UserDao;
import spring.intro.error.DatabaseConnectionException;
import spring.intro.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            LOGGER.info(String
                    .format("User #%d was successfully added", user.getId()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> userQuery = session
                    .createQuery("select u from User as u", User.class);
            return userQuery.getResultList();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Can't get list of users", e);
        }
    }

    @Override
    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> userQuery = session
                    .createQuery("select u from User as u where u.id = :id", User.class);
            userQuery.setParameter("id", id);
            return userQuery.getSingleResult();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Can't get list of users", e);
        }
    }
}
