package swingy.model.villains;

public class GenerateVillain {
	public static Villain newVillain(String villainName, int playerLevel) {
		switch (villainName) {
			case "orc":
				return (new Orc(playerLevel));
			case "goblin":
				return (new Goblin(playerLevel));
			case "skeleton":
				return (new Skeleton(playerLevel));
			case "zombie":
				return (new Zombie(playerLevel));
			default:
				break;
		}
		return null;
	}
}
