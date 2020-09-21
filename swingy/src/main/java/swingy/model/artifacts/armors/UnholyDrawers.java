package swingy.model.artifacts.armors;

public class UnholyDrawers extends Armor {

	public UnholyDrawers(String name, int playerLevel) {
		super(name);
		setDefense(10 * playerLevel);
	}
	
}
