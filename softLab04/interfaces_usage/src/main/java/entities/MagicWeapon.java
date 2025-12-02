package entities;

import properties.Shooting;

public class MagicWeapon extends Entity implements Shooting {
    private int distance;
    private int manaCost;

    public MagicWeapon(String name, int distance, int manaCost) {
        super(name);
        this.distance = distance;
        this.manaCost = manaCost;
    }

    @Override
    public void shoot(int distance) {
        System.out.println(getName() + " weapon shoot at " + distance + " distance, and spent " + manaCost +" mana");
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
