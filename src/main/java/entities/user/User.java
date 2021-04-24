package entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final int id;
    private String login;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private String email;
    private final LocalDateTime createdAt;
    private UserRole userRole;
    private UserStatus status;
}
