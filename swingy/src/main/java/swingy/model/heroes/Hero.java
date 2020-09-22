package swingy.model.heroes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import swingy.model.artifacts.armors.Armor;
import swingy.model.artifacts.helms.Helm;
import swingy.model.artifacts.weapons.Weapon;

public abstract class Hero {
	@NotBlank(message = "Name is Mandatory")
	@NotNull(message = "Name can't be null")
	@Size(min = 2, max = 10, message = "Name must be between {min} and {max}")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Name must be Alphanumeric")
	private String name;
	@NotNull (message = "Hero class can't be null")
	private String heroClass;
	@NotNull (message = "Weapon can't be null")
	protected Weapon weapon;
	@NotNull (message = "Armor can't be null")
	protected Armor armor;
	@NotNull(message = "Helm can't be null")
	protected Helm helm;
	@Min(value = 0, message = "Hero needs Hp to be alive. Must be more than 0")
	protected int hp;
	@Min(value = 0, message = "No attack, no game. Must be more than 0")
	protected int attack;
	@Min(value = 0, message = "No defense, no build. Must be more than 0")
	protected int defense;
	@Min(value = 0, message = "Xp must be more than 0")
	protected int xp;
	@Min(value = 0, message = "Level must be more than 1")
	protected int level;
	@Max(value = 20, message = "MaxHp varies between heroes. Mage = 14, Archer = 15, Knight = 20 and Samurai = 18")
	protected int maxHp;
	@AssertFalse(message = "False = alive, true = dead")
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
			System.err.println("I/O error. Failed to write to saveFile.");
			System.exit(1);
		}

		finally {
			try {
				if (saveFile != null) {
					saveFile.close();
				}
			} catch (IOException e) {
				System.err.println("I/O error. Failed to close saveFile.");
				System.exit(1);
			}
		}

	}

	public static String loadGame() {
		try {
			loadFile = new BufferedReader(new FileReader("savedGame.txt"));
			String line = loadFile.readLine();
			return line;
		} catch (IOException e) {
			System.err.println("I/O error. Failed to read loadFile.");
			System.exit(1);
		}
		finally {
			try {
				if (loadFile != null) {
					loadFile.close();
				}
			} catch (IOException e) {
				System.out.println("2I/O error. failed to close loadFile.");
				System.exit(1);
			}
		}
		return "OOps!";
	}
}
