package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Lot {
    private final int id;
    private int userId;
    private int gameId;
    private double cost;
}
