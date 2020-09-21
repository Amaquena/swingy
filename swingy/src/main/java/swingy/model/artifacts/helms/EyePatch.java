package swingy.model.artifacts.helms;

public class EyePatch extends Helm {

	public EyePatch(String name, int playerLevel) {
		super(name);
		setHealth(6 * playerLevel);
	}
	
}
