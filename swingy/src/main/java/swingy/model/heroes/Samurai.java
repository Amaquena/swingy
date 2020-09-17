package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Samurai extends Hero {

	public Samurai(String name) {
		super(name, "samurai");
		hp = 18;
		attack = 16;
		defense = 5;
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
