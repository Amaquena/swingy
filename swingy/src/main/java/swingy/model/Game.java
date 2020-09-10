package swingy.model;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;

public class Game {
	private Hero player = null;

	public void createPlayer(String heroName, String heroClass) {
		player = GenerateHero.newHero(heroName, heroClass);
	}
}
