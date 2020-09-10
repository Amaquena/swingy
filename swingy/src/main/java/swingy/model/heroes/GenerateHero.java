package swingy.model.heroes;

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
}
