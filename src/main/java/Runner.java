import project.dao.impl.CommentDAO;
import project.dao.impl.UserDAO;
import project.entities.db.comment.Comment;
import project.entities.db.user.User;
import project.entities.db.user.UserRole;

import java.util.List;

import static project.util.HibernateUtil.getSessionFactory;

public class Runner {
    public static void main(String[] args) {
        List<User> comments = new UserDAO(getSessionFactory()).readByUserRole(UserRole.TRADER);
        for (User comment : comments) {
            System.out.println(comment.toString());
        }
        System.out.println("for end");
        comments = new UserDAO(getSessionFactory()).readByUserRole(UserRole.TRADER);
        for (User comment : comments) {
            System.out.println(comment.toString());
        }
        System.out.println("for end");

//        System.out.println(UserRole.TRADER.getRole());
    }
}
