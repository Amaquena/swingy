package swingy.model.artifacts;

import swingy.model.artifacts.armors.*;
import swingy.model.artifacts.helms.*;
import swingy.model.artifacts.weapons.*;

public class GenerateArtifact {
	public static Weapon newWeapon(String weapon, int playerLevel) {
		switch (weapon) {
			case "rainbow rod":
				return (new RainbowRod(weapon, playerLevel));
			case "demonic sword":
				return (new DemonicSword(weapon, playerLevel));
			case "crimson blade":
				return (new CrimsonBlade(weapon, playerLevel));
			case "enma":
				return (new Enma(weapon, playerLevel));
			case "trident":
				return (new Trident(weapon, playerLevel));
			case "staff of zeus":
				return (new StaffOfZeus(weapon, playerLevel));
			case "green arrow":
				return (new GreenArrow(weapon, playerLevel));
			case "lightning bolt":
				return (new lightningBolt(weapon, playerLevel));
		
			default:
				break;
		}
		return null;
	}

	public static Armor newArmor(String armor, int playerLevel) {
		switch (armor) {
			case "cloth":
				return (new Cloth(armor, playerLevel));
			case "diamond armor":
				return (new DiamondArmor(armor, playerLevel));	
			case "ancient set":
				return (new AncientSet(armor, playerLevel));
			case "silk robe":
				return (new SilkRobe(armor, playerLevel));
			case "kimono":
				return (new Kimono(armor, playerLevel));
			case "unholy drawers":
				return (new UnholyDrawers(armor, playerLevel));	
			case "shitagi":
				return (new Shitagi(armor, playerLevel));
			case "dragon scales":
				return (new DragonScales(armor, playerLevel));	
		
			default:
				break;
		}
		return null;
	}

	public static Helm newHelm(String helm, int playerLevel) {
		switch (helm) {
			case "cap":
				return (new Cap(helm, playerLevel));
			case "helm of destiny":
				return (new HelmOfDestiny(helm, playerLevel));
			case "headband":
				return (new Headband(helm, playerLevel));
			case "magic hat":
				return (new MagicHat(helm, playerLevel));
			case "iron helmet":
				return (new IronHelmet(helm, playerLevel));
			case "farseer mask":
				return (new FarseerMask(helm, playerLevel));
			case "eye patch":
				return (new EyePatch(helm, playerLevel));
			case "i can't believe it's not bacon":
				return (new ICantBelieveItsNotBacon(helm, playerLevel));
		
			default:
				break;
		}
		return null;
	}
}
