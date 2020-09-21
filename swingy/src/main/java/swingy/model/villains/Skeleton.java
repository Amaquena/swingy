package swingy.model.villains;

public class Skeleton extends Villain {

	public Skeleton(int playerLevel) {
		super("skeleton");
		attack = (6 + 3) * playerLevel;
		hp = (14 + 3) * playerLevel;
		xp = 250 * playerLevel;
	}
	
}
