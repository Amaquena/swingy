package swingy.model.artifacts.helms;

public class Cap extends Helm {

	public Cap(String name, int playerLevel) {
		super(name);
		setHealth(5 * playerLevel);
	}
	
}
