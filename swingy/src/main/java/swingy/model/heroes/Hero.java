package swingy.model.heroes;

public abstract class Hero {
	private String name;
	private String heroClass;
	private int xp  = 0;
	private int level = 0;
	private int hp;
	private int weapon;
	private int armor;
	private int helm;

	public Hero(String name, String heroClass, int hp, int weapon, int armor, int helm) {
		this.name = name;
		this.heroClass = heroClass;
		this.hp = hp;
		this.weapon = weapon;
		this.armor = armor;
		this.helm = helm;
	}
	
	public String getHeroclass() {
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
	public int getWeapon() {
		return weapon;
	}

	// armor increases with level
	public int getArmor() {
		return armor;
	}

	// find a use for helm
	public int getHelm() {
		return helm;
	}
}
