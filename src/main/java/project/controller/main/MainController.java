package project.controller.main;

//import lombok.extern.slf4j.Slf4j;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

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

//
//@Slf4j
@Configuration
@RequestMapping("/main")
public class MainController {
    //private static Logger log = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/addComment/{traderId}")
    public String addComment(@PathVariable("traderId") int traderId, Model model) {
        //model.addAttribute("traderId", traderId);
        return "main/addComment";
    }

    @PostMapping("/addComment/{traderId}")
    public String addComment(@ModelAttribute("message") String message,
                             @PathVariable("traderId") int traderId) {
        new CommentDAO(getSessionFactory()).create(
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
        model.addAttribute("comments", new CommentDAO(getSessionFactory()).readByStatus(APPROVED));
        return "main/commentList";
    }

    @GetMapping("/traderList")
    public String traderList(Model model) {
        model.addAttribute("traders", new UserDAO(getSessionFactory()).readByUserRole(TRADER));
        return "main/traderList";
    }
}
