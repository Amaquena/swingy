package swingy.model.artifacts;

import swingy.model.artifacts.armors.*;
import swingy.model.artifacts.helms.*;
import swingy.model.artifacts.weapons.*;

public class GenerateArtifact {
	public static Weapon newWeapon(String weapon) {
		switch (weapon.toLowerCase()) {
			case "rainbow Rod":
				return (new RainbowRod(weapon));
			case "demonic sword":
				return (new DemonicSword(weapon));
			case "crimson blade":
				return (new CrimsonBlade(weapon));
			case "enma":
				return (new Enma(weapon));
			case "trident":
				return (new Trident(weapon));
			case "staff of zeus":
				return (new StaffOfZeus(weapon));
			case "Green arrow":
				return (new GreenArrow(weapon));
			case "lightning bolt":
				return (new lightningBolt(weapon));
		
			default:
				break;
		}
		return null;
	}

	public static Armor newArmor(String armor) {
		switch (armor.toLowerCase()) {
			case "cloth":
				return (new Cloth(armor));		
			case "diamond armor":
				return (new DiamondArmor(armor));		
			case "ancient set":
				return (new AncientSet(armor));		
			case "silk robe":
				return (new SilkRobe(armor));		
			case "kimono":
				return (new Kimono(armor));		
			case "unholy drawers":
				return (new UnholyDrawers(armor));		
			case "shitagi":
				return (new Shitagi(armor));		
			case "dragon scales":
				return (new DragonScales(armor));		
		
			default:
				break;
		}
		return null;
	}

	public static Helm newHelm(String helm) {
		switch (helm.toLowerCase()) {
			case "cap":
				return (new Cap(helm));
			case "helm of destiny":
				return (new HelmOfDestiny(helm));
			case "headband":
				return (new Headband(helm));
			case "magic hat":
				return (new MagicHat(helm));
			case "iron helmet":
				return (new IronHelmet(helm));
			case "farseer mask":
				return (new FarseerMask(helm));
			case "eye patch":
				return (new EyePatch(helm));
			case "i can't believe it's not bacon":
				return (new ICantBelieveItsNotBacon(helm));
		
			default:
				break;
		}
		return null;
	}
}
