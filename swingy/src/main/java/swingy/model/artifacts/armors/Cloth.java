package swingy.model.artifacts.armors;

public class Cloth extends Armor {

	public Cloth(String name, int playerLevel) {
		super(name);
		setDefense(6 * playerLevel);
	}
	
}
