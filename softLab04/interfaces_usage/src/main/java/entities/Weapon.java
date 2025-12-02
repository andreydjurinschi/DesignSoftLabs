package entities;

import properties.Shooting;

public class Weapon extends Entity implements Shooting {

    private int distance;

    public Weapon(String name, int distance) {
        super(name);
        this.distance = distance;
    }

    @Override
    public void shoot(int distance) {
        System.out.println(getName() + " " + " shoot at " + distance + " distance");
    }
}
