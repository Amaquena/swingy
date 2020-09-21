package swingy.model.artifacts.armors;

public class AncientSet extends Armor {

	public AncientSet(String name, int playerLevel) {
		super(name);
		setDefense(5 * playerLevel);
	}
	
}
