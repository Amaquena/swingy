package swingy.model.artifacts.armors;

public class Shitagi extends Armor {

	public Shitagi(String name, int playerLevel) {
		super(name);
		setDefense(6 * playerLevel);
	}
	
}
