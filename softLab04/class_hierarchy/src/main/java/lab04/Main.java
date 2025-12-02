package lab04;

import lab04.entities.Entity;
import lab04.entities.MagicWeapon;
import lab04.entities.Player;
import lab04.entities.Weapon;

public class Main {
    Entity player = new Player("Dimka", 100, 10);
    Entity weapon = new Weapon("Pistol", 50);
    Entity magicWeapon = new MagicWeapon("Stick", 20, 10);
}
