package entities;

import entities.base.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Match extends Entity {
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime matchTime;
    private BigDecimal homeAllocation;
    private BigDecimal awayAllocation;
    private String winner;
}
