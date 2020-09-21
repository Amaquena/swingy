package swingy.model.artifacts.helms;

public class FarseerMask extends Helm {

	public FarseerMask(String name, int playerLevel) {
		super(name);
		setHealth(7 * playerLevel);
	}
	
}
