package swingy.model.artifacts.helms;

public abstract class Helm {
	private String name;
	private int health;

	public Helm(String name) {
		this.setName(name);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
