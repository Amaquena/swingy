package swingy.model.artifacts.helms;

public class MagicHat extends Helm {

	public MagicHat(String name, int playerLevel) {
		super(name);
		setHealth(10 * playerLevel);
	}
	
}
