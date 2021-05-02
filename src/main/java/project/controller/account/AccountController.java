package project.controller.account;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dao.impl.UserDAO;
import project.entities.db.user.User;

import static java.time.LocalDateTime.now;
import static org.springframework.security.crypto.bcrypt.BCrypt.*;
import static project.entities.db.user.UserRole.USER;
import static project.entities.db.user.UserStatus.CREATED;
import static project.util.HibernateUtil.getSessionFactory;

@Configuration
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/signIn")
    public String signIn() {
        return "account/signIn";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "account/signUp";
    }

    @GetMapping("/recoveryPassword")
    public String recoveryPassword() {
        return "account/recoveryPassword";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("login") String login,
                         @ModelAttribute("password") String password) {
        UserDAO userDAO = new UserDAO(getSessionFactory());

        User user = userDAO.readByLogin(login);

        if (!user.getHashPassword().isEmpty() && checkpw(password, user.getHashPassword())) {
            return switch (user.getRole()) {
                case USER -> "redirect:/user/userHome/"+user.getId();
                case ADMIN -> "redirect:/admin/adminHome/"+user.getId();
                case TRADER -> "redirect:/trader/traderHome/"+user.getId();
                default -> "/";
            };
        }
        return "/";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("login") String login,
                         @ModelAttribute("password") String password,
                         @ModelAttribute("email") String email) {

        User user = User.builder()
                .login(login)
                .hashPassword(hashpw(password, gensalt()))
                .email(email)
                .createdAt(now())
                .role(USER)
                .status(CREATED)
                .build();

        new UserDAO(getSessionFactory()).create(user);

        return "redirect:/account/signIn";
    }
}
