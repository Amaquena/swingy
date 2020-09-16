package swingy.model.heroes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class Hero {
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 10)
	private String name;
	private String heroClass;
	protected int hp;
	protected int attack;
	protected int defense;
	protected int xp;
	protected int level;

	public Hero(String name, String heroClass) {
		this.name = name;
		this.heroClass = heroClass;
	}
	
	public String getHeroClass() {
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

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public void takeDamage(int damage) {
		hp -= damage;
	}
}
