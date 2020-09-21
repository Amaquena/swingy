package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Knight extends Hero {

	public Knight(String name) {
		super(name, "knight");
		hp = 20;
		maxHp = 20;
		attack = 15;
		defense = 6;
		xp = 0;
		level = 1;
		isDead = false;

		setWeapon(new DefaultWeapon("Default Weapon", level));
		setArmor(new DefaultArmor("Default Armor", level));
		setHelm(new DefaultHelm("Default Helm", level));

		attack += getWeapon().getAttack();
		defense += getArmor().getDefense();
		hp += getHelm().getHealth();
	}
	
}
