package lab04.entities;

public class Weapon extends Entity{
    private int shoutingDistance;

    public Weapon(String name, int shoutingDistance) {
        super(name);
        this.shoutingDistance = shoutingDistance;
    }

    public int getShoutingDistance() {
        return shoutingDistance;
    }

    public void setShoutingDistance(int shoutingDistance) {
        this.shoutingDistance = shoutingDistance;
    }
}
