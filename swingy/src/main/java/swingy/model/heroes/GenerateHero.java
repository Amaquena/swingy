package swingy.model.heroes;

public class GenerateHero {
	public static Hero newHero(String heroName, String heroClass) {
		switch (heroClass) {
			case "knight":
				return (new Knight(heroName, 20, 15, 5));
			case "mage":
				return (new Mage(heroName, 14, 14, 4));
			case "archer":
				return (new Archer(heroName, 15, 13, 5));
			case "samurai":
				return (new Samurai(heroName, 18, 16, 5));
			default:
				break;
		}
		return null;
	}
}
