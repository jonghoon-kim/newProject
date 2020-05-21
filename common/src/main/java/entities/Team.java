package entities;

import entities.base.Entity;
import lombok.Data;

@Data
public class Team extends Entity {
    private String team;
    private int win;
    private int lose;
    private String homeGround;

    @Override
    public String getKeyText() {
        return null;
    }
}
