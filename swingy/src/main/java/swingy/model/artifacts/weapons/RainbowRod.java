package swingy.model.artifacts.weapons;

public class RainbowRod extends Weapon {

	public RainbowRod(String name, int playerLevel) {
		super(name);
		setAttack(10 * playerLevel);
	}
	
}
