package swingy.model.artifacts.weapons;

public class CrimsonBlade extends Weapon {

	public CrimsonBlade(String name, int playerLevel) {
		super(name);
		setAttack(5 * playerLevel);
	}
	
}
