package swingy.model.artifacts.weapons;

public class lightningBolt extends Weapon {

	public lightningBolt(String name, int playerLevel) {
		super(name);
		setAttack(8 * playerLevel);
	}
	
}
