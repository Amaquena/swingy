package swingy.model.artifacts.helms;

public class DefaultHelm extends Helm {

	public DefaultHelm(String name, int playerLevel) {
		super(name);
		setHealth(0 * playerLevel);
	}
	
}
