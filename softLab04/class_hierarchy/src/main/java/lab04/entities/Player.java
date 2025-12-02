package lab04.entities;

public class Player extends Entity {

    private int hp;
    private int mana;

    public Player(String name, int hp, int mana) {
        super(name);
        this.hp = hp;
        this.mana = mana;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
