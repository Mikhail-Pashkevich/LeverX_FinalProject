package entities.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private final int id;
    private String message;
    private final int traderId;
    private final int authorId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CommentStatus status;

}
