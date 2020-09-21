package swingy.model.artifacts.armors;

public class Kimono extends Armor {

	public Kimono(String name, int playerLevel) {
		super(name);
		setDefense(9 * playerLevel);
	}
	
}
