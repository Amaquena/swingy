package swingy.model.heroes;

import swingy.model.artifacts.GenerateArtifact;
import swingy.model.artifacts.armors.Armor;
import swingy.model.artifacts.helms.Helm;
import swingy.model.artifacts.weapons.Weapon;

public class GenerateHero {
	public static Hero newHero(String heroName, String heroClass) {
		switch (heroClass) {
			case "knight":
				return (new Knight(heroName));
			case "mage":
				return (new Mage(heroName));
			case "archer":
				return (new Archer(heroName));
			case "samurai":
				return (new Samurai(heroName));
			default:
				break;
		}
		return null;
	}

	public static Hero loadHero(String heroData) {
		try {
			Hero player = null;
			String name = heroData.split(",")[0];
			String heroClass = heroData.split(",")[1];
			boolean isDead = Boolean.parseBoolean(heroData.split(",")[11]);
			
			switch (heroClass) {
				case "knight":
				player = (new Knight(name));
				case "mage":
				player = (new Mage(name));
				case "archer":
				player = (new Archer(name));
				case "samurai":
				player = (new Samurai(name));
				default:
				break;
			}
			player.level = Integer.parseInt(heroData.split(",")[6]);
			Weapon weapon = GenerateArtifact.newWeapon(heroData.split(",")[8], player.level);
			Armor armor = GenerateArtifact.newArmor(heroData.split(",")[9], player.level);
			Helm helm = GenerateArtifact.newHelm(heroData.split(",")[10], player.level);
			player.hp = Integer.parseInt(heroData.split(",")[2]);
			player.attack = Integer.parseInt(heroData.split(",")[3]);
			player.defense = Integer.parseInt(heroData.split(",")[4]);
			player.xp = Integer.parseInt(heroData.split(",")[5]);
			player.maxHp = Integer.parseInt(heroData.split(",")[7]);
			player.setWeapon(weapon);
			player.setArmor(armor);
			player.setHelm(helm);;
			player.isDead = isDead;
	
			return player;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error. SaveFile has been tampered with. ArrayIndexOutOfBoundsException");
			System.exit(1);
		}
		return null;
	}
}
