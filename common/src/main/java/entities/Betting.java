package entities;

import entities.base.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Betting extends Entity {
    private int bettingNo;
    private LocalDateTime matchTime;
    private int bettingMoney;
    private String bettingTeam;
    private int allocationMoney;
    private String id;

    @Override
    public String getKeyText() {
        return null;
    }
}
