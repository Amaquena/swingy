package swingy.model.villains;

public class Orc extends Villain {

	public Orc(int playerLevel) {
		super("orc");
		attack = (10 + 3) * playerLevel;
		hp = (20 + 3) * playerLevel;
		xp = 500 * playerLevel;
	}

}