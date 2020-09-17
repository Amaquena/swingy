package swingy.model;

import java.util.Random;

public class Gamble {
	private int die1;
	private int die2;
	private int die3;
	private int dieThreeSide;
	private int dieEightSide;
	private Random rand = new Random();

	public Gamble() {
		roll();
	}

	public void roll() {
		die1 = (int)(Math.random()*6) + 1;
		die1 = (int)(Math.random()*6) + 1;
		die3 = (int)(Math.random()*6) + 1;
		dieThreeSide = (int)(Math.random()*3);
		dieEightSide = (int)(Math.random()*8);
	}

	public int getDie1() {
		return die1;
	}

	public int getDie2() {
		return die2;
	}

	public int getDie3() {
		return die3;
	}

	public int getThreeSideDie() {
		return dieThreeSide;
	}

	public int getEightSideDie() {
		return dieEightSide;
	}

	public int getPairTotal() {
		return die1 + die2;
	}

	public int getTripleDiceTotal() {
		return die1 + die2 + die3;
	}

	public boolean getChance() {
		return rand.nextBoolean();
	}
}
