package swingy.model.artifacts.weapons;

public class StaffOfZeus extends Weapon {

	public StaffOfZeus(String name, int playerLevel) {
		super(name);
		setAttack(9 * playerLevel);
	}
	
}
