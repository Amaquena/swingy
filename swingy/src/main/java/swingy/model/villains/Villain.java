package swingy.model.villains;

public abstract class Villain {
	private String type;
	private int attack;
	private int hp;
	private int xp;

	public Villain(String type, int attack, int hp, int xp) {
		this.type = type;
		this.setAttack(attack);
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}