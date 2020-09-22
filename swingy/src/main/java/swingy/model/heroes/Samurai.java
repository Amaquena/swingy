package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Samurai extends Hero {

	public Samurai(String name) {
		super(name, "samurai");
		hp = 18;
		maxHp = 18;
		attack = 16;
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
