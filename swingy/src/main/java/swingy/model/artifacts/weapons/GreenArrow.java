package swingy.model.artifacts.weapons;

public class GreenArrow extends Weapon {

	public GreenArrow(String name, int playerLevel) {
		super(name);
		setAttack(4 * playerLevel);
	}

}