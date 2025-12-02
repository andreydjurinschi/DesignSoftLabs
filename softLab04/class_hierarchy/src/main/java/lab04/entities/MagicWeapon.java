package lab04.entities;

public class MagicWeapon extends Weapon{

    private int manaCost;
    public MagicWeapon(String name, int shoutingDistance, int manaCost) {
        super(name, shoutingDistance);
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
