package swingy.model.heroes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import swingy.model.artifacts.armors.Armor;
import swingy.model.artifacts.helms.Helm;
import swingy.model.artifacts.weapons.Weapon;

public abstract class Hero {
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 10)
	private String name;
	private String heroClass;
	protected Weapon weapon;
	protected Armor armor;
	protected Helm helm;
	protected int hp;
	protected int attack;
	protected int defense;
	protected int xp;
	protected int level;
	protected int maxHp;
	protected boolean isDead;

	private static BufferedWriter saveFile = null;
	private static BufferedReader loadFile = null;

	public Hero(String name, String heroClass) {
		this.name = name;
		this.heroClass = heroClass;
	}

	public boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}

	public Helm getHelm() {
		return helm;
	}

	public void setHelm(Helm helm) {
		this.helm = helm;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public int getDefense() {
		return defense;
	}

	public void takeDamage(int damage) {
		hp -= damage;
	}

	public void equipWeapon(Weapon weapon) {
		attack = attack - this.weapon.getAttack();
		this.weapon = weapon;
		attack = attack + weapon.getAttack();
	}

	public void equipArmor(Armor armor) {
		defense = defense - this.armor.getDefense();
		this.armor = armor;
		defense = defense + armor.getDefense();
	}

	public void equipHelm(Helm helm) {
		hp = hp - this.helm.getHealth();
		this.helm = helm;
		hp = hp + helm.getHealth();
	}

	public void calculateXpGain(int xp) {
		this.setXp(this.getXp() + xp);
	}

	public void calculateLevelGain(int level) {
		this.setLevel(this.getLevel() + level);

		int attackWithoutWeapon = this.attack - this.weapon.getAttack();
		int defenseWithoutArmor = this.defense - this.armor.getDefense();
		
		this.attack = ((attackWithoutWeapon * this.level) + this.weapon.getAttack());
		this.defense = ((defenseWithoutArmor * this.level) + this.armor.getDefense());
		this.hp = (maxHp * this.level) + this.helm.getHealth();
	}

	public void calculateHealthGain(int hp) {
		int hpMinusHelm = this.getHp() - this.helm.getHealth();
		int maxHpWithLevelMuliplyier = this.maxHp * this.getLevel();
		int maxHpWithLevelMuliplyierAndHelm = (maxHp * this.getLevel()) + this.helm.getHealth();

		if (hpMinusHelm < maxHpWithLevelMuliplyier) {
			if (this.getHp() < maxHpWithLevelMuliplyierAndHelm) {
				this.setHp(this.getHp() + hp);
			}
			if (maxHpWithLevelMuliplyier < this.getHp()) {
				this.setHp(maxHpWithLevelMuliplyierAndHelm);
			}
		}
	}

	public void saveGame() {
		try {
			saveFile = new BufferedWriter(new FileWriter("savedGame.txt"));
			saveFile.write(this.name + ',');
			saveFile.write(this.heroClass + ',');
			saveFile.write(String.valueOf(this.hp) + ',');
			saveFile.write(String.valueOf(this.attack) + ',');
			saveFile.write(String.valueOf(this.defense) + ',');
			saveFile.write(String.valueOf(this.xp) + ',');
			saveFile.write(String.valueOf(this.level) + ',');
			saveFile.write(String.valueOf(this.maxHp) + ',');
			saveFile.write(this.weapon.getName() + ',');
			saveFile.write(this.armor.getName() + ',');
			saveFile.write(this.helm.getName() + ',');
			saveFile.write(String.valueOf(this.isDead));
		} catch (IOException e) {
			System.err.println("I/O error");
		}

		finally {
			try {
				if (saveFile != null) {
					saveFile.close();
				}
			} catch (IOException e) {
				System.err.println("I/O error");
			}
		}

	}

	public static String loadGame() {
		try {
			loadFile = new BufferedReader(new FileReader("savedGame.txt"));
			String line = loadFile.readLine();
			return line;
			
		} catch (IOException e) {
			System.err.println("I/O error");
		}
		return "OOps!";
	}
}
