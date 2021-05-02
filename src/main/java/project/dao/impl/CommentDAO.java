package project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.dao.DAO;
import project.entities.comment.Comment;
import project.entities.comment.CommentStatus;

import java.util.List;

public class CommentDAO implements DAO<Comment, Integer> {
    private final SessionFactory factory;

    public CommentDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Comment comment) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(comment);

            session.getTransaction().commit();
        }
    }

    @Override
    public Comment read(Integer id) {
        try (Session session = factory.openSession()) {
            Comment result = session.get(Comment.class, id);

            return result != null ? result : new Comment();
        }
    }

    public List<Comment> readByStatus(CommentStatus status) {
        try (Session session = factory.openSession()) {

            return session.createQuery("from Comment where status = :status", Comment.class)
                    .setParameter("status", status.toString())
                    .list();
        }
    }

    public List<Comment> readByAuthorId(int authorId) {
        try (Session session = factory.openSession()) {

            return session.createQuery("from Comment where author.id = :authorId", Comment.class)
                    .setParameter("authorId", authorId)
                    .list();
        }
    }

    @Override
    public List<Comment> readAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Comment", Comment.class).list();
        }
    }

    @Override
    public void update(Comment comment) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(comment);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Comment comment) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(comment);

            session.getTransaction().commit();
        }
    }
}
