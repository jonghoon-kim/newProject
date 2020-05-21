package entities;

import entities.base.Entity;
import lombok.Data;

@Data
public class Member extends Entity {
    private String id;
    private String password;
    private int balance;

    @Override
    public String getKeyText() {
        return null;
    }
}
