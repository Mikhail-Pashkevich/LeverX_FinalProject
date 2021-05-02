package project.controller.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.impl.CommentDAO;
import project.dao.impl.UserDAO;
import project.entities.db.comment.Comment;
import project.entities.db.user.User;

import static java.time.LocalDateTime.now;
import static project.entities.db.comment.CommentStatus.APPROVED;
import static project.entities.db.comment.CommentStatus.DECLINED;
import static project.entities.db.user.UserRole.TRADER;
import static project.util.HibernateUtil.getSessionFactory;

@Configuration
@RequestMapping("/user")
public class UserController {
    @GetMapping("/userHome/{userId}")
    public String home(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("user", new UserDAO(getSessionFactory()).read(userId));
        return "user/userHome";
    }

    @GetMapping("/myCommentList/{userId}")
    public String myCommentList(@PathVariable("userId") int userId, Model model) {
        System.out.println("my comment list start");

        model.addAttribute("comments", new CommentDAO(getSessionFactory()).readByAuthorId(userId));
        model.addAttribute("user", new UserDAO(getSessionFactory()).read(userId));

        System.out.println("my comment list end");
        return "user/myCommentList";
    }

    @GetMapping("/traderList/{userId}")
    public String traderList(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("traders", new UserDAO(getSessionFactory()).readByUserRole(TRADER));
        model.addAttribute("user", new UserDAO(getSessionFactory()).read(userId));
        return "user/traderList";
    }

    @GetMapping("/addComment/{userId}/{traderId}")
    public String addComment(@PathVariable("userId") int userId,
                             @PathVariable("traderId") int traderId,
                             Model model) {
        model.addAttribute("traderId", traderId);
        return "main/addComment";
    }

    @PostMapping("/addComment/{userId}/{traderId}")
    public String addComment(@ModelAttribute("message") String message,
                             @PathVariable("userId") int userId,
                             @PathVariable("traderId") int traderId) {
        new CommentDAO(getSessionFactory()).create(
                Comment.builder()
                        .message(message)
                        .trader(new User(traderId))
                        .author(new User(userId))
                        .createdAt(now())
                        .updatedAt(now())
                        .status(DECLINED)
                        .build());

        return "redirect:/user/myCommentList/" + userId;
    }

    @GetMapping("/editComment/{userId}/{commentId}")
    public String editComment(@PathVariable("userId") int userId,
                              @PathVariable("commentId") int commentId,
                              Model model) {
        model.addAttribute("comment", new CommentDAO(getSessionFactory()).read(commentId));
        return "user/editComment";
    }

    @PostMapping("/editComment/{userId}/{commentId}")
    public String editComment(@ModelAttribute("comment.message") String message,
                              @PathVariable("userId") int userId,
                              @PathVariable("commentId") int commentId) {
        CommentDAO commentDAO = new CommentDAO(getSessionFactory());

        Comment comment = commentDAO.read(commentId);
        comment.setMessage(message);
        comment.setUpdatedAt(now());

        commentDAO.update(comment);
        return "redirect:/user/myCommentList/" + userId;
    }

    @PostMapping("/deleteComment/{userId}/{commentId}")
    public String deleteComment(@PathVariable("userId") int userId,
                                @PathVariable("commentId") int commentId) {
        CommentDAO commentDAO = new CommentDAO(getSessionFactory());
        commentDAO.delete(commentDAO.read(commentId));
        return "redirect:/user/myCommentList/" + userId;
    }

}
