package swingy.model.artifacts.helms;

public class HelmOfDestiny extends Helm {

	public HelmOfDestiny(String name, int playerLevel) {
		super(name);
		setHealth(9 * playerLevel);
	}
	
}
