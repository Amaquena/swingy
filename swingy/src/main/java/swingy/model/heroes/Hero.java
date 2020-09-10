package swingy.model.heroes;

public abstract class Hero {
	private String name;
	private String heroClass;
	private int hp;
	private int attack;
	private int defense;
	protected int xp;
	protected int level;

	public Hero(String name, String heroClass, int hp, int attack, int defense) {
		this.name = name;
		this.heroClass = heroClass;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
	
	public String getHeroType() {
		return heroClass;
	}

	public String getName() {
		return name;
	}

	public int getXp() {
		return xp;
	}

	public int getLevel() {
		return level;
	}

	// set different HP points for different heroClasses
	public int getHp() {
		return hp;
	}

	// set different weapons for different heroClasses
	public int getAttack() {
		return attack;
	}

	// armor increases with level
	public int getDefense() {
		return defense;
	}
}
