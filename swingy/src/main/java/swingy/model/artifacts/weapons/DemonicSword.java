package swingy.model.artifacts.weapons;

public class DemonicSword extends Weapon {

	public DemonicSword(String name, int playerLevel) {
		super(name);
		setAttack(6 * playerLevel);
	}
	
}
