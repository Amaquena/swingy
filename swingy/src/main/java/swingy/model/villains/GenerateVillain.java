package swingy.model.villains;

public class GenerateVillain {
	public static Villain newVillain(String villainName) {
		switch (villainName) {
			case "orc":
				return (new Orc());
			case "goblin":
				return (new Goblin());
			case "skeleton":
				return (new Skeleton());
			case "zombie":
				return (new Zombie());
			default:
				break;
		}
		return null;
	}
}
