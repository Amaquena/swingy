package swingy.model.villains;

public abstract class Villain {
	private String type;
	private int damage;
	private int hp;
	private int xp;

	public Villain(String type, int damage, int hp, int xp) {
		this.type = type;
		this.setDamage(damage);
		this.setHp(hp);
		this.setXp(xp);
	}

	public String getType() {
		return type;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}