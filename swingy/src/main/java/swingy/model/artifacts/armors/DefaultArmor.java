package swingy.model.artifacts.armors;

public class DefaultArmor extends Armor {

	public DefaultArmor(String name, int playerLevel) {
		super(name);
		setDefense(0 *playerLevel);
	}
	
}
