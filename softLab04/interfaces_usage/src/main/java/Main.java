import entities.Entity;
import entities.MagicWeapon;
import entities.Player;
import entities.Weapon;
import properties.Shooting;

public class Main {
    public static void main(String[] ar){
        Entity player = new Player("Dimka", 100, 10);
        Entity weapon = new Weapon("Pistol", 50);
        Entity magicWeapon = new MagicWeapon("Stick", 20, 10);

        MagicWeapon mw = (MagicWeapon) magicWeapon;

        Shooting shooting = (Shooting) magicWeapon;
        shooting.shoot(mw.getDistance());
    }
}
