package project.entities.comment;

import lombok.*;
import project.entities.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "message")
    private String message;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trader_id")
    private User trader;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommentStatus status;

    public Comment(String message, User trader, User author, LocalDateTime createdAt, LocalDateTime updatedAt, CommentStatus status) {
        this.message = message;
        this.trader = trader;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}