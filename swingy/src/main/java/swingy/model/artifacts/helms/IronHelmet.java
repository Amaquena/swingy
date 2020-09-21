package swingy.model.artifacts.helms;

public class IronHelmet extends Helm {

	public IronHelmet(String name, int playerLevel) {
		super(name);
		setHealth(8 * playerLevel);
	}
	
}
