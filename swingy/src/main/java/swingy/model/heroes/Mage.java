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
		isDead = false;

		setWeapon(new DefaultWeapon("default weapon", level));
		setArmor(new DefaultArmor("default armor", level));
		setHelm(new DefaultHelm("default helm", level));

		attack += getWeapon().getAttack();
		defense += getArmor().getDefense();
		hp += getHelm().getHealth();
	}
}
