package swingy.model.heroes;

import swingy.model.artifacts.armors.DefaultArmor;
import swingy.model.artifacts.helms.DefaultHelm;
import swingy.model.artifacts.weapons.DefaultWeapon;

public class Archer extends Hero {

	public Archer(String name) {
		super(name, "archer");
		hp = 15;
		attack = 13;
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
