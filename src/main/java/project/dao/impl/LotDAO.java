package project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.dao.DAO;
import project.entities.Lot;

import java.util.List;

public class LotDAO implements DAO<Lot, Integer> {
    private final SessionFactory factory;

    public LotDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Lot lot) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(lot);

            session.getTransaction().commit();
        }
    }

    @Override
    public Lot read(Integer id) {
        try (final Session session = factory.openSession()) {
            Lot result = session.get(Lot.class, id);

            return result != null ? result : new Lot();
        }
    }

    @Override
    public List<Lot> readAll() {
        try (final Session session = factory.openSession()) {
            return session.createQuery("from Lot", Lot.class).list();
        }
    }

    public List<Lot> readByTraderId(int traderId) {
        try (final Session session = factory.openSession()) {

            return session.createQuery("from Lot where traderId = :traderId", Lot.class)
                    .setParameter("traderId", traderId)
                    .list();
        }
    }

    @Override
    public void update(Lot lot) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(lot);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Lot lot) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(lot);

            session.getTransaction().commit();
        }
    }
}
