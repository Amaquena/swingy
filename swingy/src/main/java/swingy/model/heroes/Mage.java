package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Mage extends Hero {

	public Mage(String name) {
		super(name, "mage");
		hp = 14;
		maxHp = 14;
		attack = 14;
		defense = 4;
		xp = 0;
		level = 1;

		setWeapon(new DefaultWeapon("Default Weapon"));
		setArmor(new DefaultArmor("Default Armor"));
		setHelm(new DefaultHelm("Default Helm"));

		attack += getWeapon().getAttack();
		defense += getArmor().getDefense();
		hp += getHelm().getHealth();
	}
}
