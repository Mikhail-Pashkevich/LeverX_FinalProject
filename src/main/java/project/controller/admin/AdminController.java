package project.controller.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dao.impl.CommentDAO;
import project.dao.impl.UserDAO;
import project.entities.db.comment.Comment;
import project.entities.db.user.User;

import static project.entities.db.comment.CommentStatus.APPROVED;
import static project.entities.db.comment.CommentStatus.DECLINED;
import static project.entities.db.user.UserRole.USER;
import static project.entities.db.user.UserStatus.BLOCKED;
import static project.entities.db.user.UserStatus.CREATED;
import static project.util.HibernateUtil.getSessionFactory;

@Configuration
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/adminHome/{adminId}")
    public String adminHome(@PathVariable("adminId") int adminId, Model model) {
        model.addAttribute("admin", new UserDAO(getSessionFactory()).read(adminId));
        return "admin/adminHome";
    }

    @GetMapping("/userList/{adminId}")
    public String userList(@PathVariable("adminId") int adminId, Model model) {
        model.addAttribute("users", new UserDAO(getSessionFactory()).readByUserRole(USER));
        model.addAttribute("admin", new UserDAO(getSessionFactory()).read(adminId));
        return "admin/userList";
    }

    @GetMapping("/commentCheckList/{adminId}")
    public String commentCheckList(@PathVariable("adminId") int adminId, Model model) {
        model.addAttribute("comments", new CommentDAO(getSessionFactory()).readByStatus(DECLINED));
        model.addAttribute("admin", new UserDAO(getSessionFactory()).read(adminId));

        return "admin/commentCheckList";
    }


    @PostMapping("/unblockUser/{adminId}/{userId}")
    public String unblockUser(@PathVariable("adminId") int adminId,
                              @PathVariable("userId") int userId) {
        UserDAO userDAO = new UserDAO(getSessionFactory());

        User user = userDAO.read(userId);
        user.setStatus(CREATED);

        userDAO.update(user);
        return "redirect:/admin/userList/" + adminId;
    }

    @PostMapping("/blockUser/{adminId}/{userId}")
    public String blockUser(@PathVariable("adminId") int adminId,
                            @PathVariable("userId") int userId) {
        UserDAO userDAO = new UserDAO(getSessionFactory());

        User user = userDAO.read(userId);
        user.setStatus(BLOCKED);

        userDAO.update(user);
        return "redirect:/admin/userList/" + adminId;
    }


    @PostMapping("/deleteComment/{adminId}/{commentId}")
    public String deleteComment(@PathVariable("adminId") int adminId,
                                @PathVariable("commentId") int commentId) {
        CommentDAO commentDAO = new CommentDAO(getSessionFactory());
        commentDAO.delete(commentDAO.read(commentId));
        return "redirect:/admin/commentCheckList/" + adminId;
    }

    @PostMapping("/approveComment/{adminId}/{commentId}")
    public String approveComment(@PathVariable("adminId") int adminId,
                                 @PathVariable("commentId") int commentId) {
        CommentDAO commentDAO = new CommentDAO(getSessionFactory());

        Comment comment = commentDAO.read(commentId);
        comment.setStatus(APPROVED);

        commentDAO.update(comment);
        return "redirect:/admin/commentCheckList/" + adminId;
    }
}
