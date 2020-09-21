package swingy.model.artifacts.weapons;

public class Enma extends Weapon {

	public Enma(String name, int playerLevel) {
		super(name);
		setAttack(7 * playerLevel);
	}
	
}
