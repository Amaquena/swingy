package swingy.model.artifacts.armors;

public class DiamondArmor extends Armor {

	public DiamondArmor(String name, int playerLevel) {
		super(name);
		setDefense(7 * playerLevel);
	}
	
}
