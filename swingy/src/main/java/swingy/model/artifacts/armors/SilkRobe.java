package swingy.model.artifacts.armors;

public class SilkRobe extends Armor {

	public SilkRobe(String name, int playerLevel) {
		super(name);
		setDefense(8 * playerLevel);
	}
	
}
