package swingy.model.heroes;

public class Knight extends Hero {

	public Knight(String name, int hp, int attack, int defense) {
		super(name, "knight", hp, attack, defense);
		xp = 0;
		level = 1;
	}
	
}
