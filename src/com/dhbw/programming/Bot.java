package com.dhbw.programming;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.Player;

// not implemented yet, there might be a bot support in a future version of the game
public class Bot extends Player {

	public Bot(String name, int upperpoints, int bonus, int upperpointsAll, int lowerpoints, int totalpoints,
			Data data) {
		super(name, upperpoints, bonus, upperpointsAll, lowerpoints, totalpoints, data);
		// TODO Auto-generated constructor stub
	}

	private int[] botDice;
	ArrayList<Integer> botRollAgain;

	public void rollTheDice() {
		botDice = new int[5];
		botRollAgain = new ArrayList<Integer>();

		for (int i = 0; i < botDice.length; i++) {
			botDice[i] = (int) ((Math.random() * 6) + 1);
		}
		if (IntStream.of(botDice).sum() < 25) {
			for (int i = 0; i < botDice.length; i++) {
				if (botDice[i] < 5) {
					botRollAgain.add(botDice[i]);
				}
			}
		}

	}

}
