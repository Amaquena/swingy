package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Archer extends Hero {

	public Archer(String name) {
		super(name, "archer");
		hp = 15;
		maxHp = 15;
		attack = 13;
		defense = 5;
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
