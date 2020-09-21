package swingy.model.artifacts.armors;

public class DragonScales extends Armor {

	public DragonScales(String name, int playerLevel) {
		super(name);
		setDefense(4 * playerLevel);
	}
	
}
