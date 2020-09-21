package swingy.model.villains;

public class Zombie extends Villain {

	public Zombie(int playerLevel) {
		super("zombie");
		attack = (9 + 3) * playerLevel;
		hp = (16 + 3) * playerLevel;
		xp = 350 * playerLevel;
	}
}
