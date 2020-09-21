package swingy.model.villains;

public class Goblin extends Villain {

	public Goblin(int playerLevel) {
		super("goblin");
		attack = (6 + 3) * playerLevel;
		hp = (13 + 3) * playerLevel;
		xp = 100 * playerLevel;
	}
}
