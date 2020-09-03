package swingy.model.villains;

public abstract class Villain {
	private String type;
	private int damage;
	private int hp;

	public Villain(String type, int damage, int hp) {
		this.type = type;
		this.damage = damage;
		this.hp = hp;
	}

	public String getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public int getHp() {
		return hp;
	}
}