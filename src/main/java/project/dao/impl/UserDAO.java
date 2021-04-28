package project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.dao.DAO;
import project.entities.user.User;
import project.entities.user.UserStatus;

import java.util.List;

public class UserDAO implements DAO<User, Integer> {

    private final SessionFactory factory;

    public UserDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(User user) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public User read(Integer id) {
        try (final Session session = factory.openSession()) {
            User result = session.get(User.class, id);

            return result != null ? result : new User();
        }
    }

    @Override
    public List<User> readAll() {
        try (final Session session = factory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public User readByLogin(String login) {
        try (final Session session = factory.openSession()) {

            List<User> users = session.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .list();

            return users.isEmpty() ? new User() : users.get(0);
        }
    }

    public List<User> readByStatus(UserStatus status) {
        try (final Session session = factory.openSession()) {

            return session.createQuery("from User where status = :status", User.class)
                    .setParameter("status", status)
                    .list();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(user);

            session.getTransaction().commit();
        }
    }
}
