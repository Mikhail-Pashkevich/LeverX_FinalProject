package project.entities.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.entities.db.user.User;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trader_id")
    private User trader;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "cost")
    private double cost;
}