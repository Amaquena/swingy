package swingy.model.artifacts.weapons;

public class Trident extends Weapon {

	public Trident(String name, int playerLevel) {
		super(name);
		setAttack(6 * playerLevel);
	}	
}