package com.dhbw.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.Player;

public class Main {

	private static Scanner user_input;
	private static int[] rollable;
	public static int[] dice;
	public static int[] numbers = new int[6];
	static int playerCount;
	static int pips;
	static Object lastOperation;
	static ArrayList<Player> playerList;

	public static void main(String[] args) {

		MainFrame.main(null);

	}

	private static void getValue(int[] dice, Player player) {
		Data data = player.getData();
		Arrays.fill(numbers, 0);
		for (int i = 0; i < dice.length; i++) {
			numbers[i - 1] += 1;
		}

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] >= 4) {
				// Five-of-a-kind, called "Kniffel", results in 50 points
				if (numbers[i] == 5 && data.getKniffel().getLock() == false) {
					player.setTotalpoints(player.getTotalpoints() + data.getKniffel().getPoints());
					data.setKniffel(true);
					lastOperation = data.getKniffel();
					System.out.println("You rolled a yahtzee!");
					// Four-of-a-kind, add all pips as points
				} else {
					// switch (i) {
					// case 1:
					// if (data.getFourofakindOne().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindOne(1, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// case 2:
					// if (data.getFourofakindTwo().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindTwo(2, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// case 3:
					// if (data.getFourofakindThree().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindThree(2, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// case 4:
					// if (data.getFourofakindFour().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindFour(2, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// case 5:
					// if (data.getFourofakindFive().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindFive(2, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// case 6:
					// if (data.getFourofakindSix().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setFourofakindSix(2, pips, true);
					// System.out.println("You rolled a four-of-a-kind!");
					// }
					// break;
					// }

				}
				// if (player[1][i] == 0) {
				// player[0][i] = IntStream.of(dice).sum();
				// player[1][i] = 1;
				// System.out.println("You rolled a four-of-a-kind!");
				// }
			} else if (numbers[i] == 3) {
				if (pips < 2) {

				} else {

					// switch (i) {
					// case 1:
					// if (data.getThreeofakindOne().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindOne(1, pips, true);
					// System.out.println("You rolled a three-of-a-kind!");
					// }
					// break;
					// case 2:
					// if (data.getThreeofakindTwo().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindTwo(2, pips, true);
					// }
					// break;
					// case 3:
					// if (data.getThreeofakindThree().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindThree(3, pips, true);
					// }
					// break;
					// case 4:
					// if (data.getThreeofakindFour().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindFour(4, pips, true);
					// }
					// break;
					// case 5:
					// if (data.getThreeofakindFive().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindFive(5, pips, true);
					// }
					// break;
					// case 6:
					// if (data.getThreeofakindSix().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindSix(6, pips, true);
					// }
					// break;
					// default:
					// System.out.println("No field unlocked, error.");
					// break;
				}
			} else if (numbers[0] == 1 && numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1) {
				data.setLittleStraight(true);
				player.setTotalpoints(player.getTotalpoints() + data.getLittleStraight().getPoints());
			} else if (numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1 && numbers[5] == 1) {
				data.setBigStraight(true);
				player.setTotalpoints(player.getTotalpoints() + data.getBigStraight().getPoints());
			}
		}
	}

}
