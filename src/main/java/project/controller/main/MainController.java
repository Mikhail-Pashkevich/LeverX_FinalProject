package project.controller.main;

//import lombok.extern.slf4j.Slf4j;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.impl.CommentDAO;
import project.dao.impl.UserDAO;
import project.entities.comment.Comment;
import project.entities.user.User;

import static java.time.LocalDateTime.now;
import static project.entities.comment.CommentStatus.APPROVED;
import static project.entities.comment.CommentStatus.DECLINED;
import static project.entities.user.UserRole.TRADER;
import static project.util.HibernateUtil.getSessionFactory;


@Configuration
@RequestMapping("/main")
public class MainController {
    private final UserDAO userDAO = new UserDAO(getSessionFactory());
    private final CommentDAO commentDAO = new CommentDAO(getSessionFactory());

    @GetMapping("/addComment/{traderId}")
    public String addComment(@PathVariable("traderId") int traderId, Model model) {
        return "main/addComment";
    }

    @PostMapping("/addComment/{traderId}")
    public String addComment(@ModelAttribute("message") String message,
                             @PathVariable("traderId") int traderId) {
        commentDAO.create(
                Comment.builder()
                        .message(message)
                        .trader(new User(traderId))
                        .author(new User(1))
                        .createdAt(now())
                        .updatedAt(now())
                        .status(DECLINED)
                        .build());

        return "redirect:/main/commentList";
    }

    @GetMapping("/commentList")
    public String commentList(Model model) {
        model.addAttribute("comments", commentDAO.readByStatus(APPROVED));
        return "main/commentList";
    }

    @GetMapping("/traderList")
    public String traderList(Model model) {
        model.addAttribute("traders", userDAO.readByUserRole(TRADER));
        return "main/traderList";
    }
}