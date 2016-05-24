package com.dhbw.programming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.BigStraight;
import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.FourOfAKind;
import com.dhbw.programming.modell.FullHouse;
import com.dhbw.programming.modell.Kniffel;
import com.dhbw.programming.modell.LittleStraight;
import com.dhbw.programming.modell.Player;
import com.dhbw.programming.modell.ThreeOfAKind;

public class Main {

	private static Scanner user_input;
	private static int[] rollable;
	private static int[] dice;
	static int[] numbers = new int[6];
	static int playerCount;
	static int pips;
	static Object lastOperation;

	public static void main(String[] args) {

		// TODO create correct data

		Data data = new Data(new ThreeOfAKind(1), new ThreeOfAKind(2), new ThreeOfAKind(3), new ThreeOfAKind(4),
				new ThreeOfAKind(5), new ThreeOfAKind(6), new FourOfAKind(1), new FourOfAKind(2), new FourOfAKind(3),
				new FourOfAKind(4), new FourOfAKind(5), new FourOfAKind(6), new Kniffel(), new FullHouse(),
				new LittleStraight(), new BigStraight());
		Player player = new Player("Tom", 0, data);

		// data.setFourofakindSix(data.getFourofakindSix().getNumber(),
		// data.getFourofakindSix().getPoints(),
		// data.getFourofakindSix().getLock());

		// Bot bot = new Bot();
		// bot.introduction();

		MainFrame.main(null);

		dice = new int[5];
		user_input = new Scanner(System.in);
		// player = new int[2];
		// Points at position
		// player[0] = new int[16];
		// Lock for position
		// player[1] = new int[16];
		// Arrays.fill(player[1], 0);

		System.out.println("After the first shake, this are your dice:");
		for (int i = 0; i < dice.length; i++) {
			dice[i] = (int) ((Math.random() * 6) + 1);
		}
		Main.printDice(dice);

		Main.userRequest(player);
		Main.userRequest(player);
		pips = IntStream.of(dice).sum();
		System.out.println("You scored " + pips + " points.");
	}

	public static void printDice(int[] dice) {

		System.out.println(" 1  2  3  4  5");
		System.out.println(" " + dice[0] + "  " + dice[1] + "  " + dice[2] + "  " + dice[3] + "  " + dice[4]);
	}

	private static void again(int[] dice, Player player) {
		System.out.println("Which dice do you want to roll again? You can choose from [" + rollable
				+ "] (Enter in form of 1,3,4)");
		String rollAgain = user_input.next();
		System.out.println(rollAgain);
		String[] toRoll = rollAgain.split(",");

		for (int i = 0; i < toRoll.length; i++) {
			int j = Integer.parseInt(toRoll[i]) - 1;
			dice[j] = (int) ((Math.random() * 6) + 1);
		}

		Main.printDice(dice);

		Main.getValue(dice, player);
	}

	private static void userRequest(Player player) {
		System.out.println("Do you want to go again? (y/n)");
		String go_again = user_input.next();
		go_again = "y";
		if (go_again == "y") {
			Main.again(dice, player);
		} else if (go_again == "n") {
			System.out.println("Ok, your dice are final for this round.");
		} else {
			System.out.println("I did not understand that...");
			System.out.println(go_again);
		}
	}

	public static int[] rollDice(int[] dice) {
		for (int i = 0; i < dice.length; i++) {
			dice[i] = (int) ((Math.random() * 6) + 1);
		}
		return dice;
	}

	private static void getValue(int[] dice, Player player) {
		Data data = player.getData();
		Arrays.fill(numbers, 0);
		for (int i = 0; i < dice.length; i++) {
			numbers[i - 1] += 1;
		}

		// if (Arrays.stream(numbers).allMatch()) {
		// // Predicate.isEqual(numbers[0])) {
		//
		// }
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] >= 4) {
				// Five-of-a-kind, called "Kniffel", results in 50 points
				if (numbers[i] == 5 && data.getKniffel().getLock() == false) {
					player.setPoints(player.getPoints() + data.getKniffel().getPoints());
					data.setKniffel(true);
					lastOperation = data.getKniffel();
					System.out.println("You rolled a yahtzee!");
					// Four-of-a-kind, add all pips as points
				} else {
					switch (i) {
					case 1:
						if (data.getFourofakindOne().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindOne(1, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					case 2:
						if (data.getFourofakindTwo().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindTwo(2, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					case 3:
						if (data.getFourofakindThree().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindThree(2, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					case 4:
						if (data.getFourofakindFour().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindFour(2, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					case 5:
						if (data.getFourofakindFive().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindFive(2, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					case 6:
						if (data.getFourofakindSix().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setFourofakindSix(2, pips, true);
							System.out.println("You rolled a four-of-a-kind!");
						}
						break;
					}

				}
				// if (player[1][i] == 0) {
				// player[0][i] = IntStream.of(dice).sum();
				// player[1][i] = 1;
				// System.out.println("You rolled a four-of-a-kind!");
				// }
			} else if (numbers[i] == 3) {
				if (pips < 2) {

				} else
					switch (i) {
					case 1:
						if (data.getThreeofakindOne().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindOne(1, pips, true);
							System.out.println("You rolled a three-of-a-kind!");
						}
						break;
					case 2:
						if (data.getThreeofakindTwo().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindTwo(2, pips, true);
						}
						break;
					case 3:
						if (data.getThreeofakindThree().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindThree(3, pips, true);
						}
						break;
					case 4:
						if (data.getThreeofakindFour().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindFour(4, pips, true);
						}
						break;
					case 5:
						if (data.getThreeofakindFive().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindFive(5, pips, true);
						}
						break;
					case 6:
						if (data.getThreeofakindSix().getLock() == false) {
							player.setPoints(player.getPoints() + pips);
							data.setThreeofakindSix(6, pips, true);
						}
						break;
					default:
						System.out.println("No field unlocked, error.");
						break;
					}
			} else if (numbers[0] == 1 && numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1) {
				data.setLittleStraight(true);
				player.setPoints(player.getPoints() + data.getLittleStraight().getPoints());
			} else if (numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1 && numbers[5] == 1) {
				data.setBigStraight(true);
				player.setPoints(player.getPoints() + data.getBigStraight().getPoints());
			}
		}
	}

	public static int getPlayerCount() {
		return playerCount;
	}

	public static void setPlayerCount(int playerCount) {
		Main.playerCount = playerCount;
	}

}
