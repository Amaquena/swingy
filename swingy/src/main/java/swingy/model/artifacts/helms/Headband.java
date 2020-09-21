package swingy.model.artifacts.helms;

public class Headband extends Helm {

	public Headband(String name, int playerLevel) {
		super(name);
		setHealth(5 * playerLevel);
	}
}
