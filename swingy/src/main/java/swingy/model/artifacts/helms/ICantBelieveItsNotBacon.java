package swingy.model.artifacts.helms;

public class ICantBelieveItsNotBacon extends Helm {

	public ICantBelieveItsNotBacon(String name, int playerLevel) {
		super(name);
		setHealth(6 * playerLevel);
	}
	
}
