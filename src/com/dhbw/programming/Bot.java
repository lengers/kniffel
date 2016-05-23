package com.dhbw.programming;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Bot {

	private int[] botDice;
	ArrayList<Integer> botRollAgain;

	public void rollTheDice() {
		botDice = new int[5];
		botRollAgain = new ArrayList<Integer>();

		for (int i = 0; i < botDice.length; i++) {
			botDice[i] = (int) ((Math.random() * 6) + 1);
		}
		Main.printDice(botDice);
		if (IntStream.of(botDice).sum() < 25) {
			for (int i = 0; i < botDice.length; i++) {
				if (botDice[i] < 5) {
					botRollAgain.add(botDice[i]);
				}
			}
		}

	}

	public void introduction() {
		System.out.println("You encounter a bridge troll. The troll challenges you to a round of Yahtzee. ");
		System.out.println(
				"If you win, you may pass the bridge for free. If you lose, the toll will be your life. The game begins, as you roll first...");
		System.out.println();
	}
}
