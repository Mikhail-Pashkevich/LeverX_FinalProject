package project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.dao.DAO;
import project.entities.Game;

import java.util.List;

public class GameDAO implements DAO<Game, Integer> {
    private final SessionFactory factory;

    public GameDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Game game) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(game);

            session.getTransaction().commit();
        }
    }

    @Override
    public Game read(Integer id) {
        try (final Session session = factory.openSession()) {
            Game result = session.get(Game.class, id);

            return result != null ? result : new Game();
        }
    }

    public Game readByLogin(String name) {
        try (final Session session = factory.openSession()) {

            List<Game> users = session.createQuery("from Game where name = :name", Game.class)
                    .setParameter("name", name)
                    .list();

            return users.isEmpty() ? new Game() : users.get(0);
        }
    }

    @Override
    public List<Game> readAll() {
        try (final Session session = factory.openSession()) {
            return session.createQuery("from Game", Game.class).list();
        }
    }

    @Override
    public void update(Game game) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(game);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Game game) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(game);

            session.getTransaction().commit();
        }
    }
}
