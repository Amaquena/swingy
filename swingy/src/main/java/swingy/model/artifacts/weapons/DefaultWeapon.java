package swingy.model.artifacts.weapons;

public class DefaultWeapon extends Weapon {

	public DefaultWeapon(String name, int playerLevel) {
		super(name);
		setAttack(0 * playerLevel);
	}
}
