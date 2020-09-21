package swingy.model.villains;

public abstract class Villain {
	private String name;
	protected int attack;
	protected int hp;
	protected int xp;

	public Villain(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void takeDamage(int damage) {
		hp -= damage;
	}
}