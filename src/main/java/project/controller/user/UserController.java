package project.controller.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.impl.CommentDAO;
import project.dao.impl.UserDAO;
import project.entities.comment.Comment;
import project.entities.user.User;

import static java.time.LocalDateTime.now;
import static project.entities.comment.CommentStatus.DECLINED;
import static project.entities.user.UserRole.TRADER;
import static project.util.HibernateUtil.getSessionFactory;

@Configuration
@RequestMapping("/user")
public class UserController {
    private final UserDAO userDAO = new UserDAO(getSessionFactory());
    private final CommentDAO commentDAO = new CommentDAO(getSessionFactory());

    @GetMapping("/userHome/{userId}")
    public String home(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("user", userDAO.read(userId));
        return "user/userHome";
    }

    @GetMapping("/myCommentList/{userId}")
    public String myCommentList(@PathVariable("userId") int userId, Model model) {
        System.out.println("my comment list start");

        model.addAttribute("comments", commentDAO.readByAuthorId(userId));
        model.addAttribute("user", userDAO.read(userId));

        System.out.println("my comment list end");
        return "user/myCommentList";
    }

    @GetMapping("/traderList/{userId}")
    public String traderList(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("traders", userDAO.readByUserRole(TRADER));
        model.addAttribute("user", userDAO.read(userId));
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
        commentDAO.create(
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
        model.addAttribute("comment", commentDAO.read(commentId));
        return "user/editComment";
    }

    @PostMapping("/editComment/{userId}/{commentId}")
    public String editComment(@ModelAttribute("comment.message") String message,
                              @PathVariable("userId") int userId,
                              @PathVariable("commentId") int commentId) {
        Comment comment = commentDAO.read(commentId);
        comment.setMessage(message);
        comment.setUpdatedAt(now());

        commentDAO.update(comment);
        return "redirect:/user/myCommentList/" + userId;
    }

    @PostMapping("/deleteComment/{userId}/{commentId}")
    public String deleteComment(@PathVariable("userId") int userId,
                                @PathVariable("commentId") int commentId) {
        commentDAO.delete(commentDAO.read(commentId));
        return "redirect:/user/myCommentList/" + userId;
    }
}