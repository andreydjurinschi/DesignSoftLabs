package entities;

import properties.HasHp;
import properties.HasMana;

public class Player extends Entity implements HasHp, HasMana {
    private int hp;
    private int mana;

    public Player(String name, int hp, int mana) {
        super(name);
        this.hp = hp;
        this.mana = mana;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }
}
